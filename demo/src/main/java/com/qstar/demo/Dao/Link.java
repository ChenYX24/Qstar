package com.qstar.demo.Dao;

import com.alibaba.fastjson.JSON;
import com.qstar.demo.DataIO;
import com.qstar.demo.pojo.*;
import com.qstar.demo.pojo.Receiver.QuestionMiniUnit;
import com.qstar.demo.pojo.Result.*;
import com.qstar.demo.pojo.writeAndRead.ObjReader;
import com.qstar.demo.pojo.writeAndRead.ObjWriter;

import ch.qos.logback.classic.helpers.WithLayoutListAppender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
// import org.springframework.core.env.Environment;
// import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Repository;
// import org.springframework.web.bind.annotation.RequestHeader;
// import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

//import com.qstar.demo.DataIO;
@Repository
public class Link {
    private Map<String,User> map=new HashMap<String, User>();//储存token-用户对象对，登录时加入，表示现在在线的用户
    //每次用户登录时会生成token，从硬盘导入对象，所以这二者大致同步，每次登录就加入到map中
    private Map<Integer,Questionaire> questionaires=new HashMap<>();
    private Map<String,User> userMap=new HashMap<>();       //用于授权时需要其他用户的信息，通过邮箱获取
    private Map<Integer,FilledQuestionaire> filledQuestionaireMap=new HashMap<>();//分离填写记录和user
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
    @Value("${store.filledQuestionaireRoad}")
    private String filledQuestionaireRoad;
    /*@Value("${storeMax.mapMax}")        //三个map的上限，用于内存调度，但其实如果用户需求不大其实不太需要，锦上添花的，有想法，看情况，暂时不写
    private int mapMax;
    @Value("${storeMax.questionairesMax}")
    private int questionairesMax;
    @Value("${storeMax.userMapMax}")
    private int userMapMax;*/
    //private Environment env;

    @Autowired
    private ObjReader reader;
    @Autowired
    private ObjWriter writer;
    // @Autowired
    // private DataIO userio;
    @Autowired
    private DataIO userio;
    private int idDistribute;       //这个IDistribute应该和硬盘高度同步
    private int filledIDDistribute;     //填写记录ID的分配，也需要从文件中实时读取

    //@Value("${store.IDFile}")
    // private String CIDFile = "cidfile.txt";  //存放ID和FilledID的文件，放在base目录下
    // private String FIDFile = "fidfile.txt";
    //暂时不用
    // private Writer IDWriter=new BufferedWriter(new FileWriter(base+"/"+IDFile+".txt"));
    // private BufferedReader IDReader=new BufferedReader((new FileReader(base+"/"+IDFile+"txt")));
    // private BufferedReader CIDReader;
    // private BufferedReader FIDReader;
    // private BufferedWriter CIDWriter;
    // private BufferedWriter FIDWriter;
    public Link() throws IOException {
        map=new HashMap<String, User>();
        User user=new User();//用于测试
        map.put("Test",user);
        //System.out.println("idfilepath:" + CIDFile);
        System.out.println("questionaireRoad:" + questionaireRoad);
        /*IDProperties=PropertiesLoaderUtils.loadAllProperties("IDProperty.properties");*/
         /*Integer.parseInt(IDProperties.getProperty(IDKey));*/
        // if(new File(base+"/"+IDFile).exists()) {    //测试用
        //     CIDReader = new BufferedReader(new FileReader(base+"/"+CIDFile));
        //     idDistribute = Integer.parseInt(CIDReader.readLine());     //读取
        //     CIDReader.close();
        //     FIDReader = new BufferedReader(new FileReader(base+"/"+FIDFile));
        //     filledIDDistribute = Integer.parseInt(FIDReader.readLine());
        //     FIDReader.close();
        //     CIDWriter = new BufferedWriter(new FileWriter(base+"/"+CIDFile));
        //     FIDWriter = new BufferedWriter(new FileWriter(base+"/"+FIDFile));
        // }else{
        //     idDistribute=0;
        //     filledIDDistribute=0;
        // //}
         int a [] = reader.readQuestionaireID();
         if(a!=null) {
             idDistribute = a[0];
             filledIDDistribute = a[1];
         }else{
             idDistribute = 0;
             filledIDDistribute = 0;
         }
        System.out.println("idDistribute:"+idDistribute);
        //idDistribute=0; //不用读写文件的话至少还要赋值，否则创建不了问卷和填写记录
        //filledIDDistribute=0;
    }
    public List<QuestionaireInfo> getCreated(String token) throws IOException {//不仅要获取自己创建的，还得获取被别人授权的
            List<QuestionaireInfo> list=new ArrayList<>();
            User user=map.get(token);
            if(user == null){
                System.out.println("尚未登录");
                return null;
            }
            addInfoList(list,user.getQuestionaires());
            addAuthorityInfoList(list,user.getAllowManageQuestionaires(),0);    //添加时这个对象不是questionaire内的info对象，需要一个额外的权限值代表用户对这问卷的权限信息
            addAuthorityInfoList(list,user.getAllowEditQuestionaires(),1);
            addAuthorityInfoList(list,user.getAllowCheckQuestionaires(),2);
            return list;
    }
    public void addInfoList(List<QuestionaireInfo> list,Collection<Integer> ids) throws IOException {
        for(int id:ids){
            if(checkQuestionaire(id)){
                list.add(questionaires.get(id).getInfo());
            }
        }
    }
    public void addAuthorityInfoList(List<QuestionaireInfo> list,Collection<Integer> ids,int authority) throws IOException {
        if(ids == null){
            return;
        }
        for(int id:ids){
            if(checkQuestionaire(id)){
                QuestionaireInfo info=questionaires.get(id).getInfo();
                list.add(new QuestionaireInfo(info.getTitle(),info.getFilled(),info.isCommit(),info.getId(),authority));
            }
        }
    }
    public Result create(String title, String description/*,String filename*/,List<Question> questions,String token,boolean commit) throws IOException {
            idDistribute = userio.readQID();
            System.out.println("读出来的qid为:" + idDistribute);    
            User user=map.get(token);
            if(user == null){
                return Result.fail("尚未登录");
            }
            user.addQuestionaire(idDistribute);
            Questionaire questionaire=new Questionaire(title,description,questions,user.getName(),idDistribute,user.getHeadPic());
            questionaire.getInfo().setCommit(commit);
            questionaires.put(idDistribute,questionaire);
            System.out.println("开始写入问卷");
            writer.writeQuestionaire(questionaire); //创建时顺便写入
            System.out.println("开始写入user");
            writer.writeUser(user);
            /*public Questionaire(String title,String description,String attachFile,List<Question> questions,int id,String creatorEmail){*/
            idDistribute++;
            userio.writeQID(idDistribute);
            //CIDWriter.write(idDistribute);       //同步写入
            // if(commit){ //当需要提交时
            //     boolean b=questionaire.commit();
            //     if(!b){
            //         return Result.fail("问卷已提交，重复提交!");
            //     }
            //     System.out.println("开始写入问卷");
            //     writer.writeQuestionaire(questionaire); //创建时顺便写入
            // }
            /*updateProperties(); *///创建问卷时同步问题，暂时不用，会报错
            return Result.success(idDistribute-1);
            //return idDistribute-1;
    }
    /*public void updateProperties() throws IOException {//更新idDistribute到文件中
        IDProperties.setProperty(IDKey,idDistribute+"");
        IDProperties.store(propertiesWriter,null);
    }*/
    public ResultForCheck check(int id, String token) throws IOException {
        User user=map.get(token);
        if(user == null){
            System.out.println("尚未登录");
            return null;
        }
        if(user.hasQuestionaireID(id)) {//如果id正确中
            if(checkQuestionaire(id)) {
                Questionaire questionaire = questionaires.get(id);
                //System.out.println(JSON.toJSONString(questionaire));
                if(questionaire == null){
                    System.out.println("获取不到问卷:" + id);
                }
                //if (questionaire != null&&questionaire.allowCheck(user.getEmail())) {//是否允许查看
                    return new ResultForCheck(questionaire.getInfo().getTitle(),questionaire.getDescription(), questionaire.getQuestions());
                //}
            }
        }
        return null;
    }
    public Result save(int id, String title,String description/*,String filename*/,List<Question> list,String token,boolean commit) throws IOException {//标题的修改不应该在问卷的保存这里
        User user=map.get(token);       //综合了保存和提交两个功能
        if(user == null){
            return Result.fail("尚未登录");
        }
        if(user.hasQuestionaireID(id)) {//如果id正确中
            if(checkQuestionaire(id)) {
                Questionaire questionaire = questionaires.get(id);   //在用户map读入时，问卷map同步读入
                if (questionaire != null) {
                    /*deleteFile(questionaire.getAttachFile()); //删除原有的文件，因为已经被覆盖了*/
                    boolean suc= questionaire.save(title,description, list);
                    if(questionaire.isCommit()){
                        return Result.fail("已发布问卷!");
                    }
                    questionaire.getInfo().setCommit(commit);
                    // if(commit){ //当需要提交时
                    //     if (questionaire.allowEdit(user.getEmail())) {//允许编辑的才允许查看
                    //         if(questionaire.commit()){  //
                    //             writer.writeQuestionaire(questionaire);     //提交也会修改这个问卷对象，需要写入
                    //             return Result.success();
                    //         }
                    //         return Result.fail("已提交");
                    //     }
                    // }
                    writer.writeQuestionaire(questionaire);     //不需要写入user对象，这个方法只修改了问卷对象
                    if(suc) {
                        return Result.success();
                    }
                    return Result.fail("已提交");
                }
            }
        }
        return Result.fail("问卷ID错误");
    }
    /*public String comboAttachFileRoad(String filename){ //附件路径
        return base+"/"+attachRoad+"/"+filename;
    }
    public String comboPicFileRoad(String filename){    //图片路径
        return base+"/"+picSonRoad+"/"+filename;
    }*/
    public Result commit(int id,String token) throws IOException {  //暂时性放弃
        User user=map.get(token);
        if(user.hasQuestionaireID(id)) {//如果id正确中
            if(checkQuestionaire(id) ){
            Questionaire questionaire = questionaires.get(id);   //在用户map读入时，问卷map同步读入
            if(!questionaire.isCommit()){
                questionaire.getInfo().setCommit(true);
                return Result.success();
            }
            return Result.fail("已发布问卷！");
            }
        }
        return null;
    }
    public Result fill(int id,String token) throws IOException {//填写问卷时返回问题，并在加到自己的填写记录上    这里的ID改了，这不是问卷的ID，这是已填写的问卷的ID
            User user=map.get(token);
            if(user == null){
                return Result.fail("尚未登录");
            }
            if(checkQuestionaire(id)){
                Questionaire questionaire=questionaires.get(id);
                if (questionaire.isCommit()) {
                    if((user.containFilledID(id)&&questionaire.isMultiCommit())||!(user.containFilledID(id))) {//检验是否填写过，可以填写吗
                        //user.addFilled(filledIDDistribute);//添加填写问卷对象
                        //FilledQuestionaire filledQuestionaire=new FilledQuestionaire(questionaire.getInfo().getTitle(),questionaire.getCreator(),id,filledIDDistribute);
                        filledIDDistribute = userio.readFID();
                        filledIDDistribute++;
                        userio.writeFID(filledIDDistribute);
                        //FIDWriter.write(filledIDDistribute);
                        return Result.success(new ResultForCheckWithID(getfill(id),filledIDDistribute-1));
                        // if(commit){
                        //     return commitFill(filledQuestionaire,user);
                        // }
                        // writer.writeUser(user);//感觉IO用太多了
                        // writer.writeFilledQuestionaire(filledQuestionaire);
                        // System.out.println("success!!!!");
                        // return Result.success(new ResultForCheckWithID(getfill(id, token),filledIDDistribute-1));
                    }
                    return Result.fail("已填写过");
                }
                return Result.fail("问卷未提交，无法填写");
            }
        return Result.fail("问卷ID错误");
    }
    public ResultForCheck getfill(int id) throws IOException{//获取指定问卷
            if(checkQuestionaire(id)) { //检验这个问卷是否在文件中或questionaires中
                Questionaire questionaire = questionaires.get(id);
                if (questionaire != null && questionaire.isCommit()) {//检验是否提交
                    return new ResultForCheck(questionaire.getInfo().getTitle(),questionaire.getDescription(), questionaire.getQuestions());
                }
            }
        return null;
    }
    public List<FilledQuestionaireInfo> getFillRecord(String token) throws IOException {
        User user = map.get(token);
        if(user == null){
            System.out.println("尚未登录");
            return null;
        }
        List<Integer> list=user.getFilledQuestionaires();
        List<FilledQuestionaireInfo> filledQuestionaires=new ArrayList<>();
        for(int id:list){
            FilledQuestionaire fq = findFilled(id,token);
            if(fq == null){
                System.out.println("获取填写问卷信息失败");
                continue;
            }
            filledQuestionaires.add(findFilled(id,token).getInfo());
        }
        return filledQuestionaires;
    }
    public Result saveFill(int filledId,int id,String[] data/*, Set<Integer> set,String attach*/,String token,boolean commit) throws IOException {
        User user=map.get(token);
        if(user == null){
            System.out.println("尚未登录");
            return Result.fail("尚未登录");
        }
        FilledQuestionaire filledQuestionaire=null;
        if(!user.containFilledID(filledId)){
            user.addFilled(filledId);//添加填写问卷对象
            if(checkQuestionaire(id)){
                Questionaire questionaire=questionaires.get(id);
                filledQuestionaire=new FilledQuestionaire(questionaire.getInfo().getTitle(),questionaire.getCreator(),id,filledId);
                filledQuestionaireMap.put(filledId,filledQuestionaire);
            }
        }else{
            filledQuestionaire=findFilled(filledId, token);
        }
        //FilledQuestionaire filledQuestionaire=findFilled(filledId);
        if(filledQuestionaire!=null) {
            System.out.println("filledQuestionaire不为空!");
            if(!filledQuestionaire.isCommitted()){
                //System.out.println("之前未提交过");
                filledQuestionaire.setData(data);
                if(!user.containFilledID(filledId)){
                    user.addFilled(filledId);
                }
                writer.writeUser(user);
                if(commit){
                    return commitFill(filledQuestionaire,user);//提交问卷
                }else{
                    writer.writeFilledQuestionaire(filledQuestionaire);//只保存问卷
                }
                return Result.success();
            }
            return Result.fail("无法重复提交问卷");
        }
        return Result.fail("填写记录的ID错误");
    }
    public FilledQuestionaire findFilled(int filledID,String token) throws IOException {
        User user=map.get(token);
        if(user == null){
            System.out.println("尚未登录");
            return null;
        }
        if(user.containFilledID(filledID)){
            System.out.println("containFilledID");
            if(!filledQuestionaireMap.containsKey(filledID)){
                System.out.println("findFilled：从文件中获取问卷");
                FilledQuestionaire filledQuestionaire=reader.readFilledQuestionaire(filledID);
                if(filledQuestionaire == null){
                    System.out.println("读取失败!");
                    return null;
                }
                System.out.println("文件中读取到的问卷数据:" + filledQuestionaire.getData());
                filledQuestionaireMap.put(filledID,filledQuestionaire);
                return filledQuestionaire;
            }
            return filledQuestionaireMap.get(filledID);
        }
        return null;
    }
    /*public void deleteFilledFiles(FilledQuestionaire questionaire,Set<Integer> set){//删除填写问卷的文件，后面那个集合指定的是哪几个题是要删除的
        String[] data=questionaire.getData();
        if(set!=null) {
            for (int i : set) {
                deleteFile(data[i]);
            }
        }
    }*/
    public ResultForFill checkFill(int id, String token) throws IOException {
            String[] filled;    //已填写的数据
            FilledQuestionaire fq = findFilled(id,token);
            filled = fq.getData();
            ResultForCheck check = getfill(fq.getId());
            if (check != null) {
                return new ResultForFill(check, filled,fq.getId());
            }
        return null;
    }
    public Result commitFill(FilledQuestionaire filled, User user) throws IOException {//返回错误，可能是问卷id错误或者是已经提交  这个ID是填写记录对应的ID
        int questionaireID=filled.getId();      //这个是填写的问卷对应的ID
        if(checkQuestionaire(questionaireID)) {
            Questionaire questionaire=questionaires.get(questionaireID);
            //if (filled != null) {
                //Result result = questionaire.upload(filled.getData());
                // if(questionaire.isRecordFillerName()){//是否要记录名字
                //     questionaire.recordFiller(filled.getIndex());      //如果需要就记录
                // }
                questionaire.getInfo().addFilled();
                System.out.println("当前填写人数:"+questionaire.getInfo().getFilled());
                questionaire.upload(filled.getData());
                writer.writeQuestionaire(questionaire);
                //filled.setCommitted(result.getCode()==1);   //根据返回的状态码检验是否成功,也许filledQuestionaire也需要单独拉出来
                filled.setCommitted(true); //设置为已提交
                writer.writeFilledQuestionaire(filled);
                return Result.success();
            //}
        }
        return Result.fail("问卷ID错误");
    }
    public Result statistics(int id, String token) throws IOException {
        User user=map.get(token);
        if(user == null){
            System.out.println("尚未登录");
            return Result.fail("尚未登录");
        }
        if(user.hasQuestionaireID(id)) {
            if(checkQuestionaire(id)) {
                Questionaire questionaire=questionaires.get(id);
                // if(questionaire.allowCheck(user.getEmail())) {//只有允许查看才能看数据
                //     return Result.success(questionaire.getStatisticsResult());
                // }
                return Result.success(questionaire.getStatisticsResult());
                //return Result.fail("没有查看权限");
            }
        }
        return Result.fail("问卷ID错误");
    }



    /*public String[] storeFiles(List<MultipartFile> files) throws IOException {//储存多个文件
        String[] filenames=new String[files.size()];
        int index=0;
        for(MultipartFile file:files){
            filenames[index]=storeFile(file,true);
            index++;
        }
        return filenames;
    }*/
    /*public String storeFile(MultipartFile file,boolean choose)throws IOException{//储存附件文件
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
    }*/
    /*public void deleteFiles(String[] filenames){
        if(filenames!=null) {
            for (String filename : filenames) {
                deleteFile(filename);
            }
        }
    }*/
    /*public void deleteFile(String filename){//已经是完整路径了，不需要判断是否是附件的还是图片
        if(filename!=null) {
            File file = new File(filename);
            if (file.exists()) {
                file.delete();
            }
        }
    }*/
    public boolean checkID(int id,String token){
        User user = map.get(token);
        if(user == null){
            System.out.println("尚未登录");
            return false;
        }
        return user.hasQuestionaireID(id);
    }

    public boolean put(String token,User user) throws IOException { //用于给已登录的map添加的，给登录系统用，好得提前添加问卷和填写记录
        if(!map.containsKey(token)){
            map.put(token,user);
            List<Integer> ques=user.getQuestionaires();
            putQuestionaire(ques);
            putQuestionaire(user.getAllowManageQuestionaires());    //这三个都有可能用到
            putQuestionaire(user.getAllowEditQuestionaires());
            putQuestionaire(user.getAllowCheckQuestionaires());
            for(int id:user.getFilledQuestionaires()){
                FilledQuestionaire filledQuestionaire=findFilled(id,token);
                if(filledQuestionaire!=null){
                    System.out.println("登录自动将已填写问卷载入内存");
                    System.out.println("载入问卷id:" + filledQuestionaire.getIndex());
                    System.out.println("载入问卷数据:" + Arrays.toString(filledQuestionaire.getData()));
                    filledQuestionaireMap.put(id,filledQuestionaire);
                }else{
                    System.out.println("获取到的问卷为空:删除问卷");
                    user.getFilledQuestionaires().remove(id);
                }
            }
            return true;
        }
        return false;
    }
    public void putQuestionaire(Collection<Integer> ids){
        for(int id:ids){
            Questionaire o;
            try {
                o=reader.readQuestionaire(id);
                if(o!=null){
                    questionaires.put(id, o);     //把登录用户相关的问卷放在内存中，前提是登录成功，这个方法就是登录成功时调用的
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public User getUser(String email) throws IOException {//可能为空  用于登录系统的验证
        return reader.readUser(email);
    }

    /*public void storeMap() throws IOException {//把map存储到文件中，也许可以在关闭之前调用这个方法，免得有些数据遗漏
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
    }*/

    public boolean checkQuestionaire(int id) throws IOException {//检查是否访问的问卷存在，不存在就读入
        System.out.println("checkQuestionaire!");
        if(!questionaires.containsKey(id)){
            System.out.println("准备读取问卷");
            Questionaire o=reader.readQuestionaire(id);
            System.out.println("读取问卷成功");
            if(o!=null) {
                questionaires.put(id, o);
                System.out.println(id + ":问卷信息已经写入内存");
                return true;
            }
            return false;
        }
        System.out.println("问卷已在内存中");
        return true;
    }
    public Result setting(boolean recordName, boolean multiCommit, Date begin, Date end, Integer id, String token) throws IOException {
        if(checkQuestionaire(id)){
            User user=map.get(token);
            if(user == null){
                System.out.println("尚未登录");
                return Result.fail("尚未登录");
            }
            Questionaire questionaire=questionaires.get(id);
            if(questionaire.allowEdit(user.getEmail())) {
                questionaire.setRecordFillerName(recordName);
                questionaire.setMultiCommit(multiCommit);
                questionaire.setBegin(begin);
                questionaire.setEnd(end);
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
            if(user == null){
                System.out.println("尚未登录");
                return Result.fail("尚未登录");
            }
            Questionaire questionaire=questionaires.get(id);
            if(questionaire.allowCheck(user.getEmail())){      //只有允许查看才能看配置信息，好像又有点不合理
                return Result.success(questionaire.settingInstance());
            }
            return Result.fail("不允许查看");
        }
        return Result.fail("ID错误");
    }
    public Result authorizeManage(Integer id, String name, String email, String photo, String token) throws IOException {
        if(checkQuestionaire(id)){
            User user2 = map.get(token);
            if(user2 == null){
                System.out.println("尚未登录");
                return Result.fail("尚未登录");
            }
            if(user2.allowManage(id)) {//只有问卷的管理者才能授权给别人
                if (checkUsermap(email)) {
                    Questionaire questionaire = questionaires.get(id);
                    questionaire.addAuthorizeManageEmail(name, email, photo);
                    User user = userMap.get(email);
                    user.addManageQuestionaire(id);
                    writer.writeQuestionaire(questionaire);
                    return Result.success();
                }
                return Result.fail("邮箱错误");
            }
            return Result.fail("没有管理权限");
        }
        return Result.fail("问卷ID错误");
    }
    public Result authorizeCheck(Integer id,String name,String email,String photo,String token) throws IOException {
        if(checkQuestionaire(id)){
            User user2 = map.get(token);
            if(user2 == null){
                System.out.println("尚未登录");
                return Result.fail("尚未登录");
            }
            if(map.get(token).allowManage(id)) {//只有问卷的创建者才能授权给别人
                if (checkUsermap(email)) {
                    Questionaire questionaire = questionaires.get(id);
                    questionaire.addAuthorizeCheckEmail(name, email, photo);
                    User user = userMap.get(email);
                    user.addCheckQuestionaire(id);
                    writer.writeQuestionaire(questionaire);
                    return Result.success();
                }
                return Result.fail("邮箱错误");
            }
            return Result.fail("没有管理权限");
        }
        return Result.fail("问卷ID错误");
    }
    public Result authorizeEdit(Integer id,String name,String email,String photo,String token) throws IOException {
        if(checkQuestionaire(id)){
            User user2 = map.get(token);
            if(user2 == null){
                System.out.println("尚未登录");
                return Result.fail("尚未登录");
            }
            if(user2.allowManage(id)) {//只有问卷的创建者才能授权给别人
                if (checkUsermap(email)) {
                    Questionaire questionaire = questionaires.get(id);
                    questionaire.addAuthorizeEditEmail(name, email, photo);
                    User user = userMap.get(email);
                    user.addEditQuestionaire(id);
                    writer.writeQuestionaire(questionaire);
                    return Result.success();
                }
                return Result.fail("邮箱错误");
            }
            return Result.fail("没有管理权限");
        }
        return Result.fail("问卷ID错误");
    }
    public boolean checkUsermap(String email) throws IOException {  //检验这个邮箱是否存在，并导入到userMap中
        if(!userMap.containsKey(email)){
            User o=reader.readUser(email);
            if(o!=null){
                userMap.put(email,o);
                return true;
            }
        }
        return false;
    }
    public Result getAllowCheck(String token) throws IOException {
        User user = map.get(token);
        if(user == null){
            System.out.println("尚未登录");
            return Result.fail("尚未登录");
        }
        Set<Integer> ids=user.getAllowCheckQuestionaires();
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
            writer.writeQuestionaire(questionaire);
            return Result.success();
        }
        return Result.fail("没有编辑的权限");
    }
    public Questionaire getQuestionaireForEdit(String token,int id) throws IOException {
        User user=map.get(token);
        if(user == null){
            System.out.println("尚未登录");
            return null;
        }
        if(user.hasQuestionaireID(id)||user.containCheckQuestionaire(id)){  //是否允许编辑
            if(checkQuestionaire(id)){
                return questionaires.get(id);
            }
        }
        return null;
    }
    public Result delete(Integer id,String token){  //删除问卷
        User user=map.get(token);
        if(user == null){
            System.out.println("尚未登录");
            return null;
        }
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
            writer.writeQuestionaire(questionaire);
            return Result.success();
        }
        return Result.fail("没有编辑的权限");
    }
    public Map<String,User> getMap(){   //获取link中的map，为了验证token
        return map;
    }
    public Result getFilled(Integer id,String token) throws IOException {
        if(checkQuestionaire(id)){
            Questionaire questionaire=questionaires.get(id);
            if(questionaire.allowCheck(map.get(token).getEmail())){
                if(questionaire.isRecordFillerName()) {
                    List<String[]> datas = new ArrayList<>();
                    for (int filledID : questionaire.getFilledIDs()) {
                        datas.add(findFilled(filledID,token).getData());
                    }
                    return Result.success(new ResultForMultiFilledCheck(
                            new ResultForCheck(questionaire.getInfo().getTitle(), questionaire.getDescription(), questionaire.getQuestions()
                            ), datas));
                }
                return Result.fail("该问卷设置了匿名，不允许查看单份问卷的填写");
            }
            return Result.fail("没有查看权限");
        }
        return Result.fail("问卷ID错误");
    }

}
