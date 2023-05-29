package com.qstar.demo.pojo;

import com.qstar.demo.pojo.Result.StatisticsResult;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
public class Questionaire {//创建的问卷
    private QuestionaireInfo info;  //问卷基本信息
    private String description;     //对于问卷的描述
    private boolean haveAttach;     //是否有附件
    private String attachFile;      //附件文件
    private List<Question> questions;   //问题的集合
    private List<Statistics> statistics;    //统计数据
    private String creator;
    private List<String>  authorizeEditID;    //被授权允许编辑的用户的邮箱
    private List<String> authorizeCheckID;     //被授权允许查看的用户的邮箱
    private boolean multiCommit;            //是否允许多次提交
    private boolean recordFillerName;          //是否记录名字

    public boolean judge(String title){//判断是否为这个问卷,用于填写问卷时找到对应的问卷
        return title.equals(info.getTitle());
    }

    public Questionaire(String title,String description,String attachFile,List<Question> questions,int id,String creator){//初始化问卷对象，其中数据统计对象是和问题对象高度绑定的
        info=new QuestionaireInfo(title,id);
        this.questions=questions;
        this.description=description;
        //this.attachFile=attachFile;
        statistics=new ArrayList<>(questions.size());
        this.creator=creator;
    }
    public Questionaire(int id,String title,int filled,boolean commit){
        info = new QuestionaireInfo(id,title,filled,commit);
    }
    public boolean save(String description,String attachFile,List<Question> questions){//问卷的保存
        if(info.isCommit()){
            return false;
        }
        this.questions=questions;
        this.description=description;
        this.attachFile=attachFile;
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
            info.commit();
            return true;
        }else{
            return false;
        }
    }
    public void upload(String[] data){//上传填写数据到统计对象
        for(int i=0;i<questions.size();i++){
            statistics.get(i).add(data[i]);
        }
    }
    public StatisticsResult getStatisticsResult(int index){//获取统计结果
        if(index<questions.size()) {
            Question question = questions.get(index);
            return new StatisticsResult(info.getTitle(), question.getQuestion(), question.getType(), statistics.get(index).getData());
        }
        return null;
    }

    public void addauthorizeEditEmail(String email){
        authorizeEditID.add(email);
    }
    public void addAuthorizeCheckEmail(String email){
        authorizeCheckID.add(email);
    }
}
