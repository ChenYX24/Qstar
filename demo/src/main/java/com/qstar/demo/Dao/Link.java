package com.qstar.demo.Dao;

import com.qstar.demo.pojo.*;
import com.qstar.demo.pojo.Result.ResultForCheck;
import com.qstar.demo.pojo.Result.ResultForFill;
import com.qstar.demo.Result.StatisticsResult;
import com.qstar.demo.pojo.writeAndRead.ObjReader;
import com.qstar.demo.writeAndRead.ObjWriter;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Repository
public class Link {
    private final Map<String,User> map=new HashMap<String, User>();//储存token-用户对象对，登录时加入
    //每次用户登录时会生成token，从硬盘导入对象，所以这二者大致同步，每次登录就加入到map中
    private final Map<String,User> usernameMap=new HashMap<>();//储存用户名和用户的键值对，填写问卷需要时加入
    private String base="";//基本地址，自行设置
    private String picSonRoad="";  //存储图片的子路径（相对路径）
    private String userRoad="";     //储存用户类的文件夹（相对路径）
    private String attachRoad="";   //附件储存的文件夹
    private ObjReader reader;
    private ObjWriter writer;
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
        Questionaire questionaire=map.get(token).getQuestionaire(id);
        if(questionaire!=null) {
            deleteFile(questionaire.getAttachFile()); //删除原有的文件，因为已经被覆盖了
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
    public boolean read(String username) throws IOException {//读取指定用户的对象文件,并放入usernameMap中
        User user=reader.read(userRoad,username);
        if(user!=null) {
            usernameMap.put(username, user);
            return true;
        }else{
            return false;
        }
    }
    public ResultForCheck fill(int id,String creator,String token) throws IOException {//填写问卷时返回问题，并在加到自己的填写记录上

        if(usernameMap.keySet().contains(creator)) {
            Questionaire questionaire=usernameMap.get(creator).getQuestionaire(id);
            if(questionaire.commit()) {
                map.get(token).addFilled(creator, id, questionaire);//添加填写问卷对象
                return getfill(id, creator, token);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    public ResultForCheck getfill(int id,String creator,String token) throws IOException{//获取指定问卷
        if(!usernameMap.containsKey(creator)) {
            if(!read(creator)) {        //可能给的作者名有误，返回为空
                return null;
            }
        }
        Questionaire questionaire=usernameMap.get(creator).getQuestionaire(id);
        if(questionaire!=null) {//检验是否提交
            return new ResultForCheck(questionaire.getDescription(),questionaire.getQuestions(),questionaire.getAttachFile());
        }
        return null;
    }
    public List<FilledQuestionaireInfo> getFillRecord(String token){
        return map.get(token).getFilledInfo();
    }
    public boolean saveFill(int id,String creator,String[] data, Set<Integer> set,String attach,String token){
        FilledQuestionaire filledQuestionaire=map.get(token).findFilled(id,creator);
        if(filledQuestionaire!=null) {
            deleteFilledFiles(filledQuestionaire,set);
        filledQuestionaire.setData(data);
        deleteFile(filledQuestionaire.getAttach());
        filledQuestionaire.setAttach(attach);
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
    public ResultForFill checkFill(int id, String creator, String token) throws IOException {
            List<Question> questions;
            String[] filled;    //已填写的数据
            ResultForCheck check=getfill(id,creator,token);
            filled=map.get(token).getFilled(id,creator);
            if(check!=null) {
                return new ResultForFill(check, filled);
            }
            return null;
    }
    public boolean commitFill(int id, String creator, String token){//返回错误，可能是用户名和问卷id错误或者是已经提交
        FilledQuestionaire questionaire=map.get(token).findFilled(id,creator);
        if(questionaire!=null) {
            return questionaire.commit();
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
}
