package com.qstar.demo.pojo;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class FilledQuestionaire {//已填写的问卷
    private String creator;     //问卷作者
    private int id;    //问卷名字
    private String title;//问卷标题
    private Questionaire questionaire;
    private String[] data;  //已填写的数据，可为空
    private String attach;
    private boolean hasAttach;
    private boolean committed;  //是否已提交

    public FilledQuestionaire(String creator,int id,Questionaire questionaire){
        this.creator=creator;
        this.id=id;
        hasAttach=false;
        committed=false;
        this.questionaire=questionaire;
    }
    public FilledQuestionaireInfo getInfo(){
        return new FilledQuestionaireInfo(id,creator,committed,title);
    }
    public List<Question> getQuestions(){
        return questionaire.getQuestions();
    }
    public boolean save(String[] data){//之后再编辑时
        if(!this.committed) {
            this.data = Arrays.copyOf(data, data.length);
            return true;
        }else{
            return false;
        }
    }
    public boolean commit(){
       if(committed){//防止重复提交
           return false;
       }else{
           //上传数据到统计中
           //首先要找到对应的问卷对象
            questionaire.upload(data);
            committed=true;
           return true;
       }
    }
    public boolean verify(int id,String creator){
        return (this.id==id)&&this.creator.equals(creator);
    }
    public void setAttach(String attach){
        if(attach!=null){
            hasAttach=true;
            this.attach=attach;
        }else{
            hasAttach=false;
        }
    }
}
