package com.qstar.demo.Dao;

import com.qstar.demo.pojo.*;
import com.qstar.demo.pojo.Result.ResultForCheck;
import com.qstar.demo.pojo.Result.ResultForFill;
import com.qstar.demo.pojo.Result.StatisticsResult;
import com.qstar.demo.pojo.writeAndRead.ObjReader;
import com.qstar.demo.pojo.writeAndRead.ObjWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.qstar.demo.DataIO;
@Repository
public class Link {
    private final Map<String,User> map=new HashMap<String, User>();//储存token-用户对象对，登录时加入，表示现在在线的用户
    //每次用户登录时会生成token，从硬盘导入对象，所以这二者大致同步，每次登录就加入到map中
    private final Map<String,User> usernameMap=new HashMap<>();//储存用户邮箱和用户的键值对，填写问卷需要时加入，表示需要用到的用户数据
    private String base="";//基本地址，自行设置
    private String picSonRoad="";  //存储图片的子路径（相对路径）
    private String userRoad="";     //储存用户类的文件夹（相对路径）
    private String attachRoad="";   //附件储存的文件夹
    private ObjReader reader;
    private ObjWriter writer;
    @Autowired
    DataIO userio;
    public Link(){
        reader=new ObjReader(base);
        writer=new ObjWriter(base);
        User user=new User();//用于测试
        map.put("Test",user);
        usernameMap.put("Test",user);
    }
    public boolean verifyToken(String token){
        return map.containsKey(token);
    }       //到时候直接用拦截器检测即可，不用在link类中检测
    public List<QuestionaireInfo> getCreated(String token){
            return map.get(token).getCreateInfo();
    }
    public int create(String title, String description,String filename,List<Question> questions,String token){
            return map.get(token).addQuestionaire(title,description,filename,questions);
    }
    public ResultForCheck check(int id, String token){
        Questionaire questionaire=map.get(token).getQuestionaire(id);
        if(questionaire!=null) {
            return new ResultForCheck(questionaire.getDescription(),questionaire.getQuestions(),questionaire.getAttachFile());
        }
        return null;
    }
    public boolean save(int id, String description,String filename,List<Question> list,String token){//标题的修改不应该在问卷的保存这里
        User user=map.get(token);
        Questionaire questionaire=user.getQuestionaire(id);
        if(questionaire!=null) {
            deleteFile(questionaire.getAttachFile()); //删除原有的文件，因为已经被覆盖了
            user.setModified(true);
            return questionaire.save(description,filename,list);
        }
        return false;
    }
    public String comboAttachFileRoad(String filename){ //附件路径
        return base+"/"+attachRoad+"/"+filename;
    }
    public String comboPicFileRoad(String filename){    //图片路径
        return base+"/"+picSonRoad+"/"+filename;
    }
    public boolean commit(int id,String token) {
        Questionaire questionaire=map.get(token).getQuestionaire(id);
        if(questionaire!=null) {
            return questionaire.commit();
        }
        return false;
    }
    public boolean read(String email) throws IOException {//读取指定用户的对象文件,并放入usernameMap中  得用邮箱匹配了
        User user=reader.read(userRoad,email);
        if(user!=null) {
            usernameMap.put(email, user);
            return true;
        }else{
            return false;
        }
    }
    public ResultForCheck fill(int id,String email,String token) throws IOException {//填写问卷时返回问题，并在加到自己的填写记录上

        if(containEmail(email)) {//检验这个邮箱是否有效
            Questionaire questionaire=usernameMap.get(email).getQuestionaire(id);
            if(questionaire.commit()) {
                map.get(token).addFilled(email, id, questionaire);//添加填写问卷对象
                return getfill(id, email, token);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    public boolean containEmail(String email) throws IOException {//检验这个邮箱是否有效
        if(!usernameMap.keySet().contains(email)){
            if(!read(email)) {        //可能给的作者邮箱有误，返回为空
                return false;
            }
        }
        return true;
    }
    public ResultForCheck getfill(int id,String email,String token) throws IOException{//获取指定问卷
        User user=usernameMap.get(email);
        Questionaire questionaire=user.getQuestionaire(id);
        if(questionaire!=null&&questionaire.commit()) {//检验是否提交
            user.setModified(true);
            return new ResultForCheck(questionaire.getDescription(),questionaire.getQuestions(),questionaire.getAttachFile());
        }
        return null;
    }
    public List<FilledQuestionaireInfo> getFillRecord(String token){
        return map.get(token).getFilledInfo();
    }
    public boolean saveFill(int id,String creator,String[] data, Set<Integer> set,String attach,String token){
        User user=map.get(token);
        FilledQuestionaire filledQuestionaire=user.findFilled(id,creator);
        if(filledQuestionaire!=null) {
            deleteFilledFiles(filledQuestionaire,set);
        filledQuestionaire.setData(data);
        deleteFile(filledQuestionaire.getAttach());
        filledQuestionaire.setAttach(attach);
        user.setModified(true);
        return true;
        }
        return false;
    }
    public void deleteFilledFiles(FilledQuestionaire questionaire,Set<Integer> set){//删除填写问卷的文件
        String[] data=questionaire.getData();
        if(set!=null) {
            for (int i : set) {
                deleteFile(data[i]);
            }
        }
    }
    public ResultForFill checkFill(int id, String email, String token) throws IOException {
        if(containEmail(email)) {
            List<Question> questions;
            String[] filled;    //已填写的数据
            ResultForCheck check = getfill(id, email, token);
            filled = map.get(token).getFilled(id, email);
            if (check != null) {
                return new ResultForFill(check, filled);
            }
        }
        return null;
    }
    public boolean commitFill(int id, String creator, String token){//返回错误，可能是用户名和问卷id错误或者是已经提交
        User user =map.get(token);
        FilledQuestionaire questionaire=user.findFilled(id,creator);
        if(questionaire!=null) {
            boolean result= questionaire.commit();
            user.setModified(result);
            return result;
        }
        return false;
    }
    public StatisticsResult statistics(int index, int id, String token){
        return map.get(token).getStatistics(id,index);
    }



    public String[] storeFiles(List<MultipartFile> files) throws IOException {//储存多个文件
        String[] filenames=new String[files.size()];
        int index=0;
        for(MultipartFile file:files){
            filenames[index]=storeFile(file,true);
            index++;
        }
        return filenames;
    }
    public String storeFile(MultipartFile file,boolean choose)throws IOException{//储存附件文件
        String originalFilename=file.getOriginalFilename();
        String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename=UUID.randomUUID().toString()+suffix;
        String road;
        if(true){
            road=comboAttachFileRoad(filename);
        }else{
            road=comboPicFileRoad(filename);
        }
        file.transferTo(new File(road));
        return road;
    }
    public void deleteFiles(String[] filenames){
        if(filenames!=null) {
            for (String filename : filenames) {
                deleteFile(filename);
            }
        }
    }
    public void deleteFile(String filename){//已经是完整路径了，不需要判断是否是附件的还是图片
        if(filename!=null) {
            File file = new File(filename);
            if (file.exists()) {
                file.delete();
            }
        }
    }
    public boolean checkID(int id,String token){
        return id<map.get(token).getIdDistribute();
    }
    public void SaveUser(String token,User user){
        System.out.println("map:"+token);
        map.put(token,user);
    }

    public boolean put(String token,User user){ //用于给已登录的map添加的，给登录系统用
        if(!map.containsKey(token)){
            map.put(token,user);
            return true;
        }
        return false;
    }

    public User getUser(String email) throws IOException {//可能为空  用于登录系统的验证
        return reader.read(email);
    }

    public void storeMap() throws IOException {//把map存储到文件中
        Collection<User> users=map.values();
        Collection<User> users2=usernameMap.values();

        for(User user:users){
            if(user.isModified()){
                writer.write(user,userRoad);
            }
        }
        for(User user:users2){
            if(user.isModified()){
                writer.write(user,userRoad);
            }
        }
    }

}
