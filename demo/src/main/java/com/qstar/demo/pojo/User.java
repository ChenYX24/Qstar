package com.qstar.demo.pojo;

import com.qstar.demo.pojo.Result.StatisticsResult;
import lombok.Data;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Component
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"_id", "_name", "_email", "_passwd"})//规定转为json的格式
public class User {//用户
    //用户信息
    @JsonPropertyOrder("_id")
    private int _id;
    @JsonPropertyOrder("_name")
    private String _name;
    @JsonPropertyOrder("_email")
    private String _email;
    @JsonPropertyOrder("_passwd")
    private String _passwd;
    //private Account account;   //用户的标识  //账户类应该是独立的，User对象内部仅仅储存一个名字用于定位，因为需要账户对象登录，获取token
    @JsonIgnore
    private transient List<Questionaire> questionaires = null;    //问卷的集合
    @JsonIgnore
    private transient List<FilledQuestionaire> filledQuestionaires = null;
    @JsonIgnore
    private transient int idDistribute = 0;   //id分配，其实就是问卷创建的顺序
    public User(){
        questionaires=new ArrayList<>();
        filledQuestionaires=new ArrayList<>();
        idDistribute=0;
    }
    public User(String Name,String Email,String Passwd){
        this._name = Name;
        this._email = Email;
        this._passwd = Passwd;
    }
    public User(int Id,String Name,String Email,String Passwd){
        this._id = Id;
        this._name = Name;
        this._email = Email;
        this._passwd = Passwd;
    }
    // @JsonIgnore
    // public List<Questionaire> get_questionaires(){
    //     return questionaires;
    // }
    // @JsonIgnore
    // public List<FilledQuestionaire> get_filledQuestionaires(){
    //     return filledQuestionaires;
    // }
    // @JsonIgnore
    // public int get_idDistribute(){
    //     return idDistribute;
    // }
    @JsonIgnore
    public String getName(){
        return "tempname";
    }
    //获取问卷的概览信息，用于“我的问卷”页面
    @JsonIgnore
    public List<QuestionaireInfo> getCreateInfo(){
        List<QuestionaireInfo> infolist = new ArrayList<>(3);
        Questionaire questionaire1 = new Questionaire(1,"title1",200,true);
        Questionaire questionaire2 = new Questionaire(2,"title2",400,false);
        Questionaire questionaire3 = new Questionaire(3,"title3",600,true);
        infolist.add(questionaire1.getInfo());
        infolist.add(questionaire2.getInfo());
        infolist.add(questionaire3.getInfo());
        // List<QuestionaireInfo> infolist=new ArrayList<>(questionaires.size());
        // for(Questionaire questionaire:questionaires){
        //     infolist.add(questionaire.getInfo());
        // }
         return infolist;
    }
    @JsonIgnore
    public Questionaire getQuestionaire(int id){//可能返回空
        for(Questionaire questionare:questionaires){
            if(questionare.verify(id)){
                return questionare;
            }
        }
        return null;
    }
    @JsonIgnore
    public int addQuestionaire(String title,String description,String filename,List<Question> questions){//添加问卷
        questionaires.add(new Questionaire(title,description,filename,questions,idDistribute));
        idDistribute++;
        return idDistribute-1;
    }
    @JsonIgnore
    public StatisticsResult getStatistics(int id, int index){
        Questionaire questionaire=getQuestionaire(id);
        if(questionaire!=null){
            return questionaire.getStatisticsResult(index);
        }
        return null;
    }

    //填写流程
    @JsonIgnore
    public List<FilledQuestionaireInfo> getFilledInfo(){//获取填写记录
        List<FilledQuestionaireInfo> list=new ArrayList<>(3);
        FilledQuestionaireInfo filledQuestionaireInfo1 = new FilledQuestionaireInfo(1,"尘",false,"关于早八是否会被饿死调查");
        FilledQuestionaireInfo filledQuestionaireInfo2 = new FilledQuestionaireInfo(2,"yxw",true,"关于yxw老师的教学评价");
        FilledQuestionaireInfo filledQuestionaireInfo3 = new FilledQuestionaireInfo(3,"尘",false,"如何上金铲铲大师的调查");
        list.add(filledQuestionaireInfo1);
        list.add(filledQuestionaireInfo2);
        list.add(filledQuestionaireInfo3);
        // List<FilledQuestionaireInfo> list=new ArrayList<>(filledQuestionaires.size());
        // for(FilledQuestionaire fill:filledQuestionaires){
        //     list.add(fill.getInfo());
        // }
        return list;
    }
    @JsonIgnore
    public void addFilled(String creator,int id,Questionaire questionaire){//添加到填写记录
        filledQuestionaires.add(new FilledQuestionaire(creator,id,questionaire));
    }
    @JsonIgnore
    public FilledQuestionaire findFilled(int id,String creator){//查找指定的填写
        for(FilledQuestionaire filledQuestionaire:filledQuestionaires){
            if(filledQuestionaire.verify(id,creator)){
                return filledQuestionaire;
            }
        }
        return null;
    }
    @JsonIgnore
    public String[] getFilled(int id,String creator){//获取已填写数据
        FilledQuestionaire filledQuestionaire=findFilled(id,creator);
        if(filledQuestionaire!=null) {
            return filledQuestionaire.getData();
        }
        return null;
    }
    public String get_name()
    {
        return _name;
    }
    public String get_email()
    {
        return _email;
    }
    public String get_passwd()
    {
        return _passwd;
    }
    public int get_id()
    {
        return _id;
    }
    public void set_name(String Name)
    {
        this._name = Name;
    }
    public void set_email(String Email)
    {
        this._email = Email;
    }
    public void set_passwd(String Passwd)
    {
        this._passwd = Passwd;
    }
    public void set_id(int Id)
    {
        this._id = Id;
    }
}
