package com.qstar.demo.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Statistics {//单个问题的数据，可以是String或者integer，根据问题类型而定
    private List data;
    private Type type;
    private int count;//选项的个数

    public Statistics(Type type,int count){//传入问题的类型和选项的个数（可选）
        this.count=count;
        if(type==Type.SINGLE||type==Type.MULTIPLE){ //现在是选择题的数据提交，滑条题另外搞个
            this.type=Type.NUM;
            data=new ArrayList(count);      //滑条题的数量该怎么处理，不可能一个数值一个数值地处理，应该用范围
            for(int i=0;i<count;i++) {
                data.add(0);        //对应每个选项开始时的选择人数为0
            }
        }else{
            this.type=Type.TEXT;
            data=new ArrayList();
        }
    }
    public static Type judgeType(Type type){
        if(type==Type.SINGLE||type==Type.MULTIPLE) {
            return Type.NUM;
        }else{
            return Type.TEXT;
        }
    }
    public boolean addSingleCount(int index){//单选题调用这个方法
        if(index<count) {
            Object o = data.get(index);
            if (o instanceof Integer) {
                int i = ((Integer) o).intValue()+1 ;
                data.set(index, i);
            }
            return true;
        }else{
            return false;
        }
    }
    public boolean addCount(String choice){//可能会有多选题的添加，多选题的选项包含在一个字符串中
        int index=0;
        for(int i=0;i<choice.length();i++){
            index=(int)(choice.charAt(i)-'0');
            if(!addSingleCount(index)){
                return false;
            }
        }
        return true;
    }
    public void addOther(String info){//其他信息时的添加
        data.add(info);
    }
    public void add(String addition){//综合前两种方法的add
        if(type==Type.NUM){
                addCount(addition);
        }else{
                addOther(addition);
        }

    }

}