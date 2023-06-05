package com.qstar.demo.pojo;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"id", "title","creator", "committed","commitCount","index","data"})//规定转为json的格式
public class FilledQuestionaire {//已填写的问卷
    @JsonPropertyOrder("creator")
    private String creator;     //问卷作者，仅仅用于查看，不用于查找
    @JsonPropertyOrder("id")
    private int id;    //问卷名字
    @JsonPropertyOrder("title")
    private String title;//问卷标题
    @JsonPropertyOrder("data")
    private String[] data;  //已填写的数据，可为空
    /*private String attach;
    private boolean hasAttach;*/
    @JsonPropertyOrder("committed")
    private boolean committed;  //是否已提交
    @JsonPropertyOrder("commitCount")
    private int commitCount;     //提交次数，只有问卷允许提交多次才能大于1
    @JsonPropertyOrder("index")
    private int index;      //相当于填写的问卷的id
    public FilledQuestionaire(){

    }
    public FilledQuestionaire(String title, String creator,int id,int index){
        this.title = title;
        this.creator=creator;
        this.id=id;
        committed=false;
        commitCount=0;
        this.index=index;
        this.data=null;
    }
    // @JsonCreator
    // public FilledQuestionaire(@JsonProperty("creator") String creator,@JsonProperty("id") int id,
    // @JsonProperty("title") String title,@JsonProperty("data") String[] data,
    // @JsonProperty("committed") boolean committed,@JsonProperty("commitCount") int commitCount,@JsonProperty("index") int index){
    //     this.creator =creator;
    //     this.id = id;
    //     this.title =title;
    //     this.data = data;
    //     this.committed = committed;
    //     this.commitCount = commitCount;
    //     this.index =index;
    // }
    @JsonIgnore
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
    // public void setData(String[] data){
    //     this.data = data;
    // }
    // public String[] getData(){
    //     return this.data;
    // }
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
