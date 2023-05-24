package com.wenjuan.wenjuandemo.pojo;

import com.wenjuan.wenjuandemo.pojo.Result.StatisticsResult;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {//用户
    private Account account;   //用户的标识  //账户类应该是独立的，User对象内部仅仅储存一个名字用于定位，因为需要账户对象登录，获取token
    private List<Questionaire> questionaires;    //问卷的集合

    private List<FilledQuestionaire> filledQuestionaires;

    private int idDistribute;   //id分配，其实就是问卷创建的顺序
    public User(){
        questionaires=new ArrayList<>();
        filledQuestionaires=new ArrayList<>();
        idDistribute=0;
    }
    public String getName(){
        return "tempname";
    }
    //获取问卷的概览信息，用于“我的问卷”页面
    public List<QuestionaireInfo> getCreateInfo(){
        List<QuestionaireInfo> infolist=new ArrayList<>(questionaires.size());
        for(Questionaire questionaire:questionaires){
            infolist.add(questionaire.getInfo());
        }
        return infolist;
    }
    public Questionaire getQuestionaire(int id){//可能返回空
        for(Questionaire questionare:questionaires){
            if(questionare.verify(id)){
                return questionare;
            }
        }
        return null;
    }
    public int addQuestionaire(String title,String description,String filename,List<Question> questions){//添加问卷
        questionaires.add(new Questionaire(title,description,filename,questions,idDistribute));
        idDistribute++;
        return idDistribute-1;
    }
    public StatisticsResult getStatistics(int id, int index){
        Questionaire questionaire=getQuestionaire(id);
        if(questionaire!=null){
            return questionaire.getStatisticsResult(index);
        }
        return null;
    }

    //填写流程
    public List<FilledQuestionaireInfo> getFilledInfo(){//获取填写记录
        List<FilledQuestionaireInfo> list=new ArrayList<>(filledQuestionaires.size());
        for(FilledQuestionaire fill:filledQuestionaires){
            list.add(fill.getInfo());
        }
        return list;
    }
    public void addFilled(String creator,int id,Questionaire questionaire){//添加到填写记录
        filledQuestionaires.add(new FilledQuestionaire(creator,id,questionaire));
    }
    public FilledQuestionaire findFilled(int id,String creator){//查找指定的填写
        for(FilledQuestionaire filledQuestionaire:filledQuestionaires){
            if(filledQuestionaire.verify(id,creator)){
                return filledQuestionaire;
            }
        }
        return null;
    }
    public String[] getFilled(int id,String creator){//获取已填写数据
        FilledQuestionaire filledQuestionaire=findFilled(id,creator);
        if(filledQuestionaire!=null) {
            return filledQuestionaire.getData();
        }
        return null;
    }

}
