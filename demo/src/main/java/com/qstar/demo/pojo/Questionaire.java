package com.qstar.demo.pojo;

import com.qstar.demo.pojo.Receiver.QuestionMiniUnit;
import com.qstar.demo.pojo.Result.ResultForSetting;
import com.qstar.demo.pojo.Result.StatisticsResult;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.*;

@Data
@NoArgsConstructor
public class Questionaire {//创建的问卷
    private QuestionaireInfo info;  //问卷基本信息
    private String description;     //对于问卷的描述
    private boolean haveAttach;     //是否有附件
    private String attachFile;      //附件文件
    private List<Question> questions;   //问题的集合
    private List<Statistics> statistics;    //统计数据
    private String creatorEmail;                 //创建者的邮箱
    private Set<String>  authorizeEditID=new HashSet<>();    //被授权允许编辑的用户的邮箱
    private Set<String> authorizeCheckID=new HashSet<>();     //被授权允许查看的用户的邮箱
    private boolean multiCommit;            //是否允许多次提交
    private boolean recordFillerName;          //是否记录名字
    private Set<String> fillerNames=new HashSet<>();            //填写的用户名
    private boolean modified;               //是否被修改
    private Date begin;                 //开始时间
    private Date end;                   //结束时间


    public boolean judge(String title){//判断是否为这个问卷,用于填写问卷时找到对应的问卷
        return title.equals(info.getTitle());
    }

    public Questionaire(String title,String description,String attachFile,List<Question> questions,int id,String creatorEmail){//初始化问卷对象，其中数据统计对象是和问题对象高度绑定的
        info=new QuestionaireInfo(title,id);
        this.questions=questions;
        this.description=description;
        //this.attachFile=attachFile;
        statistics=new ArrayList<>(questions.size());
        this.creatorEmail=creatorEmail;
        this.modified=true;     //刚开始初始化时需要主动被写入
    }
    public Questionaire(int id,String title,int filled,boolean commit){
        info = new QuestionaireInfo(id,title,filled,commit);
    }
    public boolean save(String title,String description,String attachFile,List<Question> questions,String editorEmail){//问卷的保存，只有被授权的用户才能编辑
        if(info.isCommit()){
            return false;
        }
        if(!allowEdit(editorEmail)){//如果不是问卷的创建者或者被授权者，不能编辑
            return false;
        }
        this.info.setTitle(title);
        this.questions=questions;
        this.description=description;
        this.attachFile=attachFile;
        this.modified=true;
        if(attachFile!=null){   //可能在保存中把上个存档的附件删除了，所以从true变false是有可能的
            haveAttach=false;
        }else{
            haveAttach=true;
        }
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
                if (question.getExtra() != null) {
                    statistics.add(new Statistics(question.getType(), question.getExtra().length));
                } else {
                    statistics.add(new Statistics(question.getType(), 1));
                }
            }
            this.modified=true;
            info.commit();
            return true;
        }else{
            return false;
        }
    }
    public boolean upload(String[] data){//上传填写数据到统计对象
        if(Arrays.asList(data).contains(null)) {//检测是否还有数据没有填完的
            Date date=new Date();
            if((begin!=null&&date.after(begin)||begin==null)) {       //验证是否在规定时间内提交，如果没设置时间，可以直接过
                if ((end != null && date.before(end)) || end == null) {
                    for (int i = 0; i < questions.size(); i++) {
                        statistics.get(i).add(data[i]);
                    }
                    this.modified = true;
                    return true;
                }
            }
        }
        return false;
    }
    public void uploadUsername(String filledName){
        fillerNames.add(filledName);
    }
    public StatisticsResult getStatisticsResult(int index){//获取统计结果
        if(index<questions.size()) {
            Question question = questions.get(index);
            return new StatisticsResult(info.getTitle(), question.getQuestion(), question.getType(), statistics.get(index).getData());
        }
        return null;
    }

    public void addAuthorizeEditEmail(String email){
        authorizeEditID.add(email);
    }
    public void addAuthorizeCheckEmail(String email){
        authorizeCheckID.add(email);
    }
    public boolean allowEdit(String email){
        return authorizeEditID.contains(email)||email.equals(creatorEmail);
    }
    public boolean allowCheck(String email){
        if(allowEdit(email)){
            return true;
        }
        return authorizeCheckID.contains(email);        //前面的判断可以编辑已经判断过了
    }
    public ResultForSetting settingInstance(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");       //这个如果直接返回date会有时区的问题，所以最好返回字符串
        return new ResultForSetting(recordFillerName,multiCommit,sdf.format(begin),sdf.format(end));
    }
    public void partlySave(String title, String description, QuestionMiniUnit[] unit){//保留修改
        this.info.setTitle(title);
        this.description=description;
        for(int i=0;i<questions.size();i++){
            Question question=questions.get(i);
            question.setQuestion(unit[i].getQuestion());
            if(question.getType()==Type.SINGLE||question.getType()==Type.MULTIPLE||question.getType()==Type.SLIDE) {//判断是否是选择题
                question.setExtra(unit[i].getChoices());
            }
        }
    }
    public void uncommit(){//把内部已经保存的数据全部清空,置为未提交的状态
        statistics.clear();
        info.setCommit(false);
    }
}
