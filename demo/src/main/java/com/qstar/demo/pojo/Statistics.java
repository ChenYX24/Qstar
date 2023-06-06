package com.qstar.demo.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@Data
@JsonPropertyOrder({"count","type","data"})
public class Statistics {//单个问题的数据，可以是String或者integer，根据问题类型而定
    private List data;
    private Type type;
    private int count;//选项的个数
    @JSONCreator
    public Statistics(List data,Type type,int count){
        this.data = data;
        this.type = type;
        this.count = count;
    }
    public Statistics(Type type,int count){//传入问题的类型和选项的个数（可选）
        this.count=count;
        this.type = type;
        if(type==Type.SINGLE||type==Type.MULTIPLE){ //现在是选择题的数据提交，滑条题另外搞个
            data=new ArrayList(count);      //滑条题的数量该怎么处理，不可能一个数值一个数值地处理，应该用范围
            for(int i=0;i<count;i++) {
                data.add(0);        //对应每个选项开始时的选择人数为0
            }
        }else{
            data=new ArrayList();
        }
    }
    public boolean addSingleCount(int index){//单选题调用这个方法
        if(index<count) {
            Object o = data.get(index);
            System.out.println("index:"+index);
            if (o instanceof Integer) {
                int i = ((Integer) o).intValue()+1 ;
                System.out.println("插入前:");
                System.out.println(data);
                data.set(index, i);
                System.out.println("插入后:");
                System.out.println(data);
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
        if(type==Type.SINGLE || type==Type.MULTIPLE){
                addCount(addition);
        }else{
                addOther(addition);
        }

    }

}