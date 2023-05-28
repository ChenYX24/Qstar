package com.qstar.demo.pojo;

import lombok.Data;

import java.io.File;
import java.util.*;

@Data
public class Question {//问题项类
    private String question;
    private Type type;
    private String[] extra;
    private boolean havePic;
    private String[] pictures;//储存选项的图片
    private Map<Integer,String[]> flag;//可能会存在一个问题是另外问题的前提条件
}
