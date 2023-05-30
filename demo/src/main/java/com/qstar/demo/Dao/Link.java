package com.qstar.demo.Dao;

import com.qstar.demo.pojo.*;
import com.qstar.demo.pojo.Receiver.QuestionMiniUnit;
import com.qstar.demo.pojo.Result.*;
import com.qstar.demo.pojo.writeAndRead.ObjReader;
import com.qstar.demo.pojo.writeAndRead.ObjWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

import com.qstar.demo.DataIO;
@Repository
public class Link {
    private Map<String,User> map=new HashMap<String, User>();//储存token-用户对象对，登录时加入，表示现在在线的用户
    //每次用户登录时会生成token，从硬盘导入对象，所以这二者大致同步，每次登录就加入到map中
    private Map<Integer,Questionaire> questionaires=new HashMap<>();
    private Map<String,User> userMap=new HashMap<>();
    @Value("${store.base}")
    private String base;//基本地址，自行设置
    @Value("${store.picSonRoad}")
    private String picSonRoad;  //存储图片的子路径（相对路径）
    @Value("${store.userRoad}")
    private String userRoad;     //储存用户类的文件夹（相对路径）
    @Value("${store.attachRoad}")
    private String attachRoad;   //附件储存的文件夹
    @Value("${store.questionaireRoad}")
    private String questionaireRoad;
    @Value("${storeMax.mapMax}")        //三个map的上限，用于内存调度，但其实如果用户需求不大其实不太需要，锦上添花的，有想法，看情况，暂时不写
    private int mapMax;
    @Value("${storeMax.questionairesMax}")
    private int questionairesMax;
    @Value("${storeMax.userMapMax}")
    private int userMapMax;
    private Environment env;
    @Autowired
    private ObjReader reader;
    @Autowired
    private ObjWriter writer;
    @Autowired
    DataIO userio;
    private int idDistribute;       //这个IDistribute应该和硬盘高度同步
    private Properties IDProperties;    //保存ID数值的文件
    private final static String IDKey="idDistribute";
    private Writer propertiesWriter=new BufferedWriter(new FileWriter("IDProperty.properties"));
    public Link() throws IOException {
        User user=new User();//用于测试
        map.put("Test",user);
        IDProperties=PropertiesLoaderUtils.loadAllProperties("IDProperty.properties");
        idDistribute= Integer.parseInt(IDProperties.getProperty(IDKey));
    }
    public boolean verifyToken(String token){
        return map.containsKey(token);
    }       //到时候直接用拦截器检测即可，不用在link类中检测
    public List<QuestionaireInfo> getCreated(String token){
            return map.get(token).getCreateInfo();
    }
    public int create(String title, String description,String filename,List<Question> questions,String token) throws IOException {
            User user=map.get(token);
            user.addQuestionaire(idDistribute);
            questionaires.put(idDistribute,new Questionaire(title,description,filename,questions,idDistribute,user.get_email()));
            /*public Questionaire(String title,String description,String attachFile,List<Question> questions,int id,String creatorEmail){*/
            idDistribute++;
            updateProperties(); //创建问卷时同步问题
            return idDistribute-1;
    }
    public void updateProperties() throws IOException {//更新idDistribute到文件中
        IDProperties.setProperty(IDKey,idDistribute+"");
        IDProperties.store(propertiesWriter,null);
    }
    public ResultForCheck check(int id, String token) throws IOException {
        User user=map.get(token);
        if(user.hasQuestionaireID(id)) {//如果id正确中
            if(checkQuestionaire(id)) {
                Questionaire questionaire = questionaires.get(id);
                if (questionaire != null&&questionaire.allowCheck(user.get_email())) {//是否允许查看
                    return new ResultForCheck(questionaire.getInfo().getTitle(),questionaire.getDescription(), questionaire.getQuestions(), questionaire.getAttachFile());
                }
            }
        }
        return null;
    }
    public boolean save(int id, String title,String description,String filename,List<Question> list,String token) throws IOException {//标题的修改不应该在问卷的保存这里
        User user=map.get(token);
        if(map.get(token).hasQuestionaireID(id)) {//如果id正确中
            if(checkQuestionaire(id)) {
                Questionaire questionaire = questionaires.get(id);   //在用户map读入时，问卷map同步读入
                if (questionaire != null) {
                    deleteFile(questionaire.getAttachFile()); //删除原有的文件，因为已经被覆盖了
                    return questionaire.save(title,description, filename, list,user.get_email());
                }
            }
        }
        return false;
    }
    public String comboAttachFileRoad(String filename){ //附件路径
        return base+"/"+attachRoad+"/"+filename;
    }
    public String comboPicFileRoad(String filename){    //图片路径
        return base+"/"+picSonRoad+"/"+filename;
    }
    public Result commit(int id,String token) throws IOException {
        User user=map.get(token);
        if(user.hasQuestionaireID(id)) {//如果id正确中
            if(checkQuestionaire(id) ){
            Questionaire questionaire = questionaires.get(id);   //在用户map读入时，问卷map同步读入
            if (questionaire != null&&questionaire.allowEdit(user.get_email())) {//允许编辑的才允许查看
                if(questionaire.commit()){
                    return Result.success();
                }
                return Result.fail("已提交");
            }
            }
        }
        return Result.fail("问卷ID错误");
    }
    public Result fill(int id,String token) throws IOException {//填写问卷时返回问题，并在加到自己的填写记录上    这里的ID改了，这不是问卷的ID，这是已填写的问卷的ID
            User user=map.get(token);
            if(checkQuestionaire(id)){
                Questionaire questionaire=questionaires.get(id);
                if (questionaire.isCommit()) {
                    if((user.containFilledID(id)&&questionaire.isMultiCommit())||!(user.containFilledID(id))) {//检验是否填写过，可以填写吗
                        int index=user.addFilled(questionaire.getCreatorEmail(), id, questionaire);//添加填写问卷对象
                        user.setModified(true);
                        return Result.success(new ResultForCheckWithID(getfill(id, token),index));
                    }
                    return Result.fail("用户没有权限");
                }
                return Result.fail("未提交");
            }
        return Result.fail("问卷ID错误");
    }
    public ResultForCheck getfill(int id,String token) throws IOException{//获取指定问卷
            if(checkQuestionaire(id)) { //检验这个问卷是否在文件中或questionaires中
                Questionaire questionaire = questionaires.get(id);
                if (questionaire != null && questionaire.commit()) {//检验是否提交
                    return new ResultForCheck(questionaire.getInfo().getTitle(),questionaire.getDescription(), questionaire.getQuestions(), questionaire.getAttachFile());
                }
            }
        return null;
    }
    public List<FilledQuestionaireInfo> getFillRecord(String token){
        return map.get(token).getFilledInfo();
    }
    public boolean saveFill(int id,String[] data, Set<Integer> set,String attach,String token){
        User user=map.get(token);
        FilledQuestionaire filledQuestionaire=user.findFilled(id);
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
    public void deleteFilledFiles(FilledQuestionaire questionaire,Set<Integer> set){//删除填写问卷的文件，后面那个集合指定的是哪几个题是要删除的
        String[] data=questionaire.getData();
        if(set!=null) {
            for (int i : set) {
                deleteFile(data[i]);
            }
        }
    }
    public ResultForFill checkFill(int id, String token) throws IOException {
            List<Question> questions;
            String[] filled;    //已填写的数据
            ResultForCheck check = getfill(id, token);
            filled = map.get(token).getFilled(id);
            if (check != null) {
                return new ResultForFill(check, filled);
            }
        return null;
    }
    public Result commitFill(int filledID, String token) throws IOException {//返回错误，可能是问卷id错误或者是已经提交  这个ID是填写记录对应的ID
        User user =map.get(token);
        FilledQuestionaire filled=user.findFilled(filledID);      //如果允许多次填写，那得分清楚是第几次
        int questionaireID=filled.getId();      //这个是填写的问卷对应的ID
        if(checkQuestionaire(questionaireID)) {
            Questionaire questionaire=questionaires.get(questionaireID);
            if (filled != null) {
                Result result = questionaire.upload(filled.getData());
                if(questionaire.isRecordFillerName()){//是否要记录名字
                    questionaire.uploadUsername(user.get_name());
                }
                filled.setCommitted(result.getCode()==1);   //根据返回的状态码检验是否成功
                return result;
            }
        }
        return Result.fail("问卷ID错误");
    }
    public Result statistics(int index, int id, String token) throws IOException {
        User user=map.get(token);
        if(user.hasQuestionaireID(id)) {
            if(checkQuestionaire(id)) {
                Questionaire questionaire=questionaires.get(id);
                if(questionaire.allowCheck(user.get_email())) {//只有允许查看才能看数据
                    return Result.success(questionaire.getStatisticsResult(index));
                }
                return Result.fail("没有查看权限");
            }
        }
        return Result.fail("问卷ID错误");
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
        return map.get(token).hasQuestionaireID(id);
    }
    public void SaveUser(String token,User user){
        System.out.println("map:"+token);
        map.put(token,user);
    }

    public boolean put(String token,User user) throws IOException { //用于给已登录的map添加的，给登录系统用
        if(!map.containsKey(token)){
            map.put(token,user);
            List<Integer> ques=user.getQuestionaires();
            for(int id:ques){
                Object o=reader.readQuestionaire(id);
                if(o instanceof Questionaire){
                    questionaires.put(id,(Questionaire) o);     //把登录用户相关的问卷放在内存中，前提是登录成功，这个方法就是登录成功时调用的
                }
            }
            return true;

        }
        return false;
    }

    public User getUser(String email) throws IOException {//可能为空  用于登录系统的验证
        return (User)reader.read(email);
    }

    public void storeMap() throws IOException {//把map存储到文件中，也许可以在关闭之前调用这个方法，免得有些数据遗漏
        Collection<User> users=map.values();
        Collection<Questionaire> ques =questionaires.values();
        for(User user:users){
            if(user.isModified()){
                writer.write(user,userRoad);
                user.setModified(false);
            }
        }
        for(Questionaire questionaire:ques){
            if(questionaire.isModified()){
                writer.write(questionaire,questionaire.getInfo().getId());
                questionaire.setModified(false);
            }
        }
    }

    public boolean checkQuestionaire(int id) throws IOException {//检查是否访问的问卷存在，不存在就读入
        if(!questionaires.containsKey(id)){
            Object o=reader.readQuestionaire(id);
            if(o!=null&&o instanceof  Questionaire) {
                questionaires.put(id, (Questionaire)o);
                return true;
            }
            return false;
        }
        return true;
    }
    public Result setting(boolean recordName, boolean multiCommit, Date begin, Date end, Integer id, String token) throws IOException {
        if(checkQuestionaire(id)){
            User user=map.get(token);
            Questionaire questionaire=questionaires.get(id);
            if(questionaire.allowEdit(user.get_email())) {
                questionaire.setRecordFillerName(recordName);
                questionaire.setMultiCommit(multiCommit);
                questionaire.setBegin(begin);
                questionaire.setEnd(end);
                questionaire.setModified(true);
                return Result.success();
            }else{
                return Result.fail("不允许编辑");
            }
        }
        return Result.fail("ID错误");
    }
    public Result getSetting(Integer id,String token) throws IOException {
        if(checkQuestionaire(id)){
            User user=map.get(token);
            Questionaire questionaire=questionaires.get(id);
            if(questionaire.allowCheck(user.get_email())){      //只有允许查看才能看配置信息，好像又有点不合理
                return Result.success(questionaire.settingInstance());
            }
            return Result.fail("不允许查看");
        }
        return Result.fail("ID错误");
    }
    public Result authorizeCheck(Integer id,String email,String token) throws IOException {
        if(checkQuestionaire(id)&&map.get(token).hasQuestionaireID(id)){    //只有问卷的创建者才能授权给别人
            if(checkUsermap(email)) {
                Questionaire questionaire = questionaires.get(id);
                questionaire.addAuthorizeCheckEmail(email);
                User user=userMap.get(email);
                user.addCheckQuestionaire(id);
                user.setModified(true);
                return Result.success();
            }
            return Result.fail("邮箱错误");
        }
        return Result.fail("问卷ID错误");
    }
    public Result authorizeEdit(Integer id,String email,String token) throws IOException {
        if(checkQuestionaire(id)&&map.get(token).hasQuestionaireID(id)){
            if(checkUsermap(email)) {
                Questionaire questionaire = questionaires.get(id);
                questionaire.addAuthorizeEditEmail(email);
                User user=userMap.get(email);
                user.addEditQuestionaire(id);
                user.setModified(true);
                return Result.success();
            }
            return Result.fail("邮箱错误");
        }
        return Result.fail("问卷ID错误");
    }
    public boolean checkUsermap(String email) throws IOException {  //检验这个邮箱是否存在，并导入到userMap中
        if(!userMap.containsKey(email)){
            Object o=reader.readUser(email);
            if(o!=null&&o instanceof User){
                userMap.put(email,(User)o);
                return true;
            }
        }
        return false;
    }
    public Result getAllowCheck(String token) throws IOException {
        Set<Integer> ids=map.get(token).getAllowCheckQuestionaires();
        List<QuestionaireInfo> list=new ArrayList<>();
        for(int i:ids){
            if(checkQuestionaire(i)){
                Questionaire questionaire = questionaires.get(i);
                list.add(questionaire.getInfo());
            }
        }
        return Result.success(list);
    }
    public Result getAllowEdit(String token) throws IOException {
        Set<Integer> ids=map.get(token).getAllowEditQuestionaires();
        List<QuestionaireInfo> list=new ArrayList<>();
        for(int i:ids){
            if(checkQuestionaire(i)){
                Questionaire questionaire = questionaires.get(i);
                list.add(questionaire.getInfo());
            }
        }
        return Result.success(list);
    }
    public Result saveEdit(int id,String title, String description, QuestionMiniUnit[] unit, String token) throws IOException {
        Questionaire questionaire=getQuestionaireForEdit(token,id);
        if(questionaire!=null) {
            questionaire.partlySave(title, description, unit);
            questionaire.setModified(true);
            return Result.success();
        }
        return Result.fail("没有编辑的权限");
    }
    public Questionaire getQuestionaireForEdit(String token,int id) throws IOException {
        User user=map.get(token);
        if(user.hasQuestionaireID(id)||user.containCheckQuestionaire(id)){  //是否允许编辑
            if(checkQuestionaire(id)){
                return questionaires.get(id);
            }
        }
        return null;
    }
    public Result delete(Integer id,String token){  //删除问卷
        User user=map.get(token);
        if(user.hasQuestionaireID(id)){
            if(user.containEditQuestionaire(id)) {
                user.deleteQuestionaire(id);
                deleteQuestionaire(id);
                return Result.success();
            }
            return Result.fail("没有编辑的权限");
        }
        return Result.fail("问卷ID错误");
    }
    public void deleteQuestionaire(Integer id){//删除问卷在文件中的备份
        File file=new File(getQuestionaireRoad(id+""));
        if(file.exists()){
            file.delete();
        }
        if(questionaires.containsKey(id)){
            questionaires.remove(id);
        }
    }
    public String getQuestionaireRoad(String filename){
        return base+"/"+questionaireRoad+"/"+filename+".txt";
    }

    public Result withdraw(Integer id,String token) throws IOException {
        Questionaire questionaire=getQuestionaireForEdit(token,id);
        if(questionaire!=null){
            questionaire.uncommit();
            questionaire.setModified(true);
            return Result.success();
        }
        return Result.fail("没有编辑的权限");
    }
    public Map<String,User> getMap(){   //获取link中的map，为了验证token
        return map;
    }

}
