package com.qstar.demo.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qstar.demo.pojo.Receiver.AuthorityReceive;
import com.qstar.demo.pojo.Receiver.QuestionMiniUnit;
import com.qstar.demo.pojo.Result.ChoiceResult;
import com.qstar.demo.pojo.Result.Result;
import com.qstar.demo.pojo.Result.ResultForSetting;
import com.qstar.demo.pojo.Result.StatisticsResult;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.*;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Questionaire {//创建的问卷
    private QuestionaireInfo info;  //问卷基本信息
    private String description;     //对于问卷的描述
/*    private boolean haveAttach;     //是否有附件
    private String attachFile;      //附件文件*/
    private List<Question> questions;   //问题的集合
    private List<Statistics> statistics;    //统计数据
    private String creator;
    private Set<UserProfile> authorizeManageID=new HashSet<>();     //被授权允许管理的用户
    private Set<UserProfile>  authorizeEditID=new HashSet<>();    //被授权允许编辑的用户的邮箱
    private Set<UserProfile> authorizeCheckID=new HashSet<>();     //被授权允许查看的用户的邮箱
    private boolean multiCommit;            //是否允许多次提交
    private boolean recordFillerName;          //是否记录名字
    private List<Integer> filledIDs=new ArrayList<>();  //只有当记录名字时才会使用
    private Date begin;                 //开始时间
    private Date end;                   //结束时间
    @JsonCreator
    public Questionaire(int id,String title,String description,List<Question> questions,List<Statistics> statistics){
        info=new QuestionaireInfo(title,id);
        this.questions=questions;
        this.description=description;
        //this.attachFile=attachFile;
        //statistics=new ArrayList<>(questions.size());
        this.statistics=statistics;
        System.out.println(questions.size());
    }
    public Questionaire(String title,String description/*,String attachFile*/,List<Question> questions,String creator,int id,String photo){//初始化问卷对象，其中数据统计对象是和问题对象高度绑定的
        info=new QuestionaireInfo(title,id);
        this.questions=questions;
        this.description=description;
        //this.attachFile=attachFile;
        //statistics=new ArrayList<>(questions.size());
        this.statistics=new ArrayList<>();
        System.out.println(questions.size());
        this.creator=creator;
        /*authorizeManageID.add(new UserProfile(name,creatorEmail,photo));*/
    }
    public Questionaire(int id,String title,int filled,boolean commit){
        info = new QuestionaireInfo(title,filled,commit,id);
    }
    public boolean save(String title,String description/*,String attachFile*/,List<Question> questions){//问卷的保存，只有被授权的用户才能编辑
        // if(info.isCommit()){
        //     System.out.println("commit:true");
        //     return false;
        // }
        // if(!allowEdit(editorEmail)){//如果不是问卷的创建者或者被授权者，不能编辑
        //     return false;
        // }
        this.info.setTitle(title);
        this.questions=questions;
        this.description=description;
        /*this.attachFile=attachFile;*/
        /*if(attachFile!=null){   //可能在保存中把上个存档的附件删除了，所以从true变false是有可能的
            haveAttach=false;
        }else{
            haveAttach=true;
        }*/
        return true;
    }
    //编辑问卷，要先获取问卷已填写的数据
    //这个方法还可以用于获取填写需要的问卷
    public List<Question> getSaved(){
        return questions;
    }
    //编辑问卷，还得找到需要的问卷，这时判断这个问卷是否是需要的问卷
    //这个方法还可以用于找到填写问卷时需要的问卷
    public boolean verify(int id){
        return info.verify(id);
    }
    public boolean commit(){//问卷的提交
        if(!info.isCommit()) {
            int index = 0;
            for (Question question : questions) {
                if (question.getChoice() != null) {
                    statistics.add(new Statistics(question.getType(), question.getChoice().length));
                } else {
                    statistics.add(new Statistics(question.getType(), 1));
                }
            }
            info.setCommit();
            return true;
        }else{
            return false;
        }
    }
    public boolean isCommit(){
        return info.isCommit();
    }
    public Result upload(String[] data){//上传填写数据到统计对象
        //if(checkUnfill(data)) {//检测是否还有数据没有填完的，但是可能有些问题不是必须要填的
            //Date date=new Date();
            //if((begin!=null&&date.after(begin)||begin==null)) {       //验证是否在规定时间内提交，如果没设置时间，可以直接过
               // if ((end != null && date.before(end)) || end == null) {
                    
                    if(statistics.size() == 0){
                        for(int i = 0;i < questions.size();i++){
                            Type t = questions.get(i).getType();
                            System.out.println(t);
                            int num = questions.get(i).getChoice().length;
                            Statistics s = new Statistics(t, num);
                            statistics.add(s);
                        }
                    }
                    System.out.println("写入前:");
                    System.out.println(statistics);
                    System.out.println("开始写入数据");
                    for (int i = 0; i < questions.size(); i++) {
                        statistics.get(i).add(data[i]);
                    }
                    System.out.println(statistics);
                    return Result.success();
                //}
            //}
            //return Result.fail("已超时");
        //}
        //return Result.fail("有些问题没有回答");
    }
    public boolean checkUnfill(String[] data){//检验没填的问题，如果为空，除非为不必填或者有前提条件没有满足，否则返回没有false
        // for(int i=0;i<questions.size();i++){
        //     if(data[i]==null&&questions.get(i).isNeed()&&checkSatisfiedPromise(data,questions.get(i).getLogic())){
        //         return false;
        //     }
        // }
        return true;
    }
    public boolean checkSatisfiedPromise(String[] data,Map<Integer,String> map){//
        for(int i:map.keySet()){//可能会有多个前提条件
            if(!data[i].contains(map.get(i))) {      //可能是只要选择a,但是这是个多选题，需要选择abc，只要包含即可
                return false;
            }
        }
        return true;
    }
    @JsonIgnore
    public List<StatisticsResult> getStatisticsResult(){//获取多个问题的统计结果
        List<StatisticsResult> statisticsResults=new ArrayList<>();
        for(int index=0;index<questions.size();index++) {
            Question question = questions.get(index);
            Type type=question.getType();
            List<ChoiceResult> choices=new ArrayList<>();
            if(type==Type.SINGLE||type==Type.MULTIPLE){
                String[] choice=question.getChoice();       //获取选项信息
                List<Integer> list=( List<Integer>)statistics.get(index).getData();
                for(int i=0;i<choice.length;i++) {
                    choices.add(new ChoiceResult(list.get(i),choice[i]));
                }
            }else{
                List<String> list=statistics.get(index).getData();
                for(String str:list){
                    choices.add(new ChoiceResult(str));
                }
            }
            statisticsResults.add(new StatisticsResult(question.getQuestion(), question.getType(), choices));
        }
        return statisticsResults;
    }

    public void addAuthorizeEditEmail(String name,String email,String photo){
        authorizeEditID.add(new UserProfile(name,email,photo));
    }
    public void addAuthorizeCheckEmail(String name,String email,String photo){
        authorizeCheckID.add(new UserProfile(name,email,photo));
    }
    public void addAuthorizeManageEmail(String name,String email,String photo){
        authorizeCheckID.add(new UserProfile(name,email,photo));
    }
    public boolean allowEdit(String email){
        return authorizeEditID.contains(email)||authorizeManageID.contains(email);
    }
    public boolean allowCheck(String email){
        if(allowEdit(email)){
            return true;
        }
        return authorizeCheckID.contains(email);        //前面的判断可以编辑已经判断过了
    }
    public ResultForSetting settingInstance(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");       //这个如果直接返回date会有时区的问题，所以最好返回字符串
        return new ResultForSetting(sdf.format(begin),sdf.format(end),multiCommit,recordFillerName,returnAuthorityReceive());
    }
    public List<AuthorityReceive> returnAuthorityReceive(){
        List<AuthorityReceive> authorityReceives=new ArrayList<>();
        for(UserProfile profile:authorizeManageID){
            authorityReceives.add(new AuthorityReceive(profile.getName(),profile.getEmail(),profile.getPhoto(),0));
        }
        for(UserProfile profile:authorizeEditID){
            authorityReceives.add(new AuthorityReceive(profile.getName(),profile.getEmail(),profile.getPhoto(),1));
        }
        for(UserProfile profile:authorizeCheckID){
            authorityReceives.add(new AuthorityReceive(profile.getName(),profile.getEmail(),profile.getPhoto(),2));
        }
        return authorityReceives;
    }
    public void partlySave(String title, String description, QuestionMiniUnit[] unit){//保留修改
        this.info.setTitle(title);
        this.description=description;
        for(int i=0;i<questions.size();i++){
            Question question=questions.get(i);
            question.setQuestion(unit[i].getQuestion());
            if(question.getType()==Type.SINGLE||question.getType()==Type.MULTIPLE) {//判断是否是选择题
                question.setChoice(unit[i].getChoices());
            }
        }
    }
    public void uncommit(){//把内部已经保存的数据全部清空,置为未提交的状态
        statistics.clear();
        info.setCommit(false);
    }
    public void recordFiller(int filledid){
            filledIDs.add(filledid);
    }

    public boolean setEnd(Date end) {
        if(new Date().after(end)){
            return false;
        }
        this.end = end;
        return true;
    }
}
