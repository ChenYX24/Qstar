package com.qstar.demo.pojo;

import com.qstar.demo.pojo.Result.StatisticsResult;
import lombok.Data;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Component
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"_id", "_name", "_email", "_passwd","_phonenumber","_location"})//规定转为json的格式
public class User {//用户
    //用户信息
    @JsonPropertyOrder("_id")
    private int _id;//用户id
    @JsonPropertyOrder("_name")
    private String _name;//用户名
    @JsonPropertyOrder("_email")
    private String _email;//用户邮箱
    @JsonPropertyOrder("_passwd")
    private String _passwd;//用户密码
    @JsonPropertyOrder("_phonenumber")
    private String _phonenumber;//用户手机号
    @JsonPropertyOrder("_location")
    private String _location;//用户位置
    //private Account account;   //用户的标识  //账户类应该是独立的，User对象内部仅仅储存一个名字用于定位，因为需要账户对象登录，获取token
    @JsonIgnore
    private transient List<Integer> questionaires = null;    //问卷id的集合
    @JsonIgnore
    private transient List<FilledQuestionaire> filledQuestionaires = null;
    private Set<Integer> allowManageQuestionaires;        //允许管理的问卷
    @JsonIgnore
    private transient Set<Integer> allowCheckQuestionaires;         //允许查看的问卷
    @JsonIgnore
    private transient Set<Integer> allowEditQuestionaires;        //允许编辑的问卷
    @JsonIgnore
    int indexDistribute;
    public User(){
        questionaires=new ArrayList<>();
        filledQuestionaires=new ArrayList<>();
        indexDistribute=0;
    }
    public User(String Name,String Email,String Passwd){
        this._name = Name;
        this._email = Email;
        this._passwd = Passwd;
        indexDistribute=0;
    }
    public User(int Id,String Name,String Email,String Passwd,String Phonenumber,String Location){
        this._id = Id;
        this._name = Name;
        this._email = Email;
        this._passwd = Passwd;
        this._phonenumber = Phonenumber;
        this._location = Location;
        indexDistribute=0;
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
    public boolean hasQuestionaireID(int id){//可能返回空，检验是否拥有这个问卷
        if(questionaires.contains(id)) {
            return true;
        }
        return false;
    }
    @JsonIgnore
    public void addQuestionaire(int id){//添加问卷
        questionaires.add(id);
    }
    /*@JsonIgnore
    *//*public StatisticsResult getStatistics(int id, int index){
        Questionaire questionaire=getQuestionaire(id);
        if(questionaire!=null){
            return questionaire.getStatisticsResult(index);
        }
        return null;
    }*/

    //填写流程
    @JsonIgnore
    public List<FilledQuestionaireInfo> getFilledInfo(){//获取填写记录
        List<FilledQuestionaireInfo> list=new ArrayList<>(3);
       /* FilledQuestionaireInfo filledQuestionaireInfo1 = new FilledQuestionaireInfo(1,"尘",false,"关于早八是否会被饿死调查");
        FilledQuestionaireInfo filledQuestionaireInfo2 = new FilledQuestionaireInfo(2,"yxw",true,"关于yxw老师的教学评价");
        FilledQuestionaireInfo filledQuestionaireInfo3 = new FilledQuestionaireInfo(3,"尘",false,"如何上金铲铲大师的调查");*/
        /*list.add(filledQuestionaireInfo1);
        list.add(filledQuestionaireInfo2);
        list.add(filledQuestionaireInfo3);*/
        // List<FilledQuestionaireInfo> list=new ArrayList<>(filledQuestionaires.size());
        // for(FilledQuestionaire fill:filledQuestionaires){
        //     list.add(fill.getInfo());
        // }
        return list;
    }
    @JsonIgnore
    public int addFilled(String creator,int id,Questionaire questionaire){//添加到填写记录
        filledQuestionaires.add(new FilledQuestionaire(creator,id,questionaire,indexDistribute));
        indexDistribute++;
        return indexDistribute-1;
    }
    @JsonIgnore
    public FilledQuestionaire findFilled(int id){//查找指定的填写
        if(id<filledQuestionaires.size()) {
            return filledQuestionaires.get(id);
        }
        return null;
    }
    @JsonIgnore
    public String[] getFilled(int id){//获取已填写数据
        FilledQuestionaire filledQuestionaire=findFilled(id);
        if(filledQuestionaire!=null) {
            return filledQuestionaire.getData();
        }
        return null;
    }
    public boolean containFilledID(int id){       //这个用户是否填写过这个ID的问卷
        for(FilledQuestionaire filledQuestionaire:filledQuestionaires){
            if(filledQuestionaire.getId()==id){
                return true;
            }
        }
        return false;
    }

    public void addManageQuestionaire(Integer id) {allowManageQuestionaires.add(id);
    }
    public void addCheckQuestionaire(Integer id){
        allowCheckQuestionaires.add(id);
    }
    public void addEditQuestionaire(Integer id){
        allowEditQuestionaires.add(id);
    }
    public void deleteQuestionaire(Integer id){
        questionaires.remove(id);
    }
    public boolean containCheckQuestionaire(Integer id){
        return allowCheckQuestionaires.contains(id);
    }
    public boolean containEditQuestionaire(Integer id){
        return allowEditQuestionaires.contains(id);
    }
    public boolean allowManage(Integer id){
        return hasQuestionaireID(id)||allowManageQuestionaires.contains(id);
    }
    //各种get、set函数
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
    public String get_phonenumber(){
        return _phonenumber;
    }
    public String get_location(){
        return _location;
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
    public void set_phonenumber(String phonenumber){
        this._phonenumber = phonenumber;
    }
    public void set_location(String location){
        this._location = location;
    }


}
