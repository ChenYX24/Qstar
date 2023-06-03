package com.qstar.demo.pojo;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class FilledQuestionaire {//已填写的问卷
    private String creator;     //问卷作者，仅仅用于查看，不用于查找
    private int id;    //问卷名字
    private String title;//问卷标题
    private String[] data;  //已填写的数据，可为空
    /*private String attach;
    private boolean hasAttach;*/
    private boolean committed;  //是否已提交
    private int commitCount;     //提交次数，只有问卷允许提交多次才能大于1
    private int index;      //相当于填写的问卷的id

    public FilledQuestionaire(String creator,int id,int index){
        this.creator=creator;
        this.id=id;
        committed=false;
        commitCount=0;
        this.index=index;
    }
    public FilledQuestionaireInfo getInfo(){
        return new FilledQuestionaireInfo(id,creator,committed,title,commitCount);
    }
    public boolean save(String[] data){//之后再编辑时
        if(!this.committed) {
            this.data = Arrays.copyOf(data, data.length);
            return true;
        }else{
            return false;
        }
    }
    /*public boolean commit(){
       if(committed){//防止重复提交
           return false;
       }else{
           //上传数据到统计中
           //首先要找到对应的问卷对象
            questionaire.upload(data);
            committed=true;
           return true;
       }
    }*/
    public void setCommitted(boolean committed){//设置这个填写记录是否提交
        if(this.committed==false){
            this.committed=committed;
        }
        if(committed==true){
            commitCount++;
        }
    }
}
