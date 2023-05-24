package com.wenjuan.wenjuandemo.pojo;

import lombok.Data;

@Data
public class QuestionaireInfo {//单个问卷的相关数据，能在"/created"访问时直接返回
    private String title;
    private int filled;//填写的人数
    private boolean commit;
    private int id;

    public QuestionaireInfo(String title,int id) {
        this.title = title;
        this.filled=0;
        this.commit = false;
        this.id=id;
    }

    public void commit() {
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
