package com.qstar.demo.pojo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
// import com.fasterxml.jackson.annotation.JsonProperty;
// import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"id", "filled", "title", "commit"})//规定转为json的格式
public class QuestionaireInfo {//单个问卷的相关数据，能在"/created"访问时直接返回
    private String title;
    private int filled;//填写的人数
    @JsonPropertyOrder("commit")
    private boolean commit;
    private int id;
    public QuestionaireInfo(){

    }
    public QuestionaireInfo(String title,int id) {
        this.title = title;
        this.filled=0;
        this.commit = false;
        this.id=id;
    }
    public QuestionaireInfo(int id,String title,int filled,boolean commit){
        this.id = id;
        this.title = title;
        this.filled = filled;
        this.commit = commit;
    }
    public boolean getCommit(){
        return commit;
    }
    public void setCommit() {
        this.commit =true;
    }
    public void addFilled(){    //添加填写的份数，一般一个用户填写问卷之后直接调用这个方法
        filled++;
    }

    public String getTitle() {
        return title;
    }
    public int getFilled() {
        return filled;
    }
    public boolean isCommit() {
        return commit;
    }
    public boolean verify(int id){
        return this.id==id;
    }

}
