package com.qstar.demo.pojo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.File;
import java.util.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"question","type","choice"})
public class Question {//问题项类
    @JsonPropertyOrder("question")
    private String question;
    @JsonPropertyOrder("type")
    private Type type;
    @JsonPropertyOrder("choice")
    private String[] choice;
    /*private boolean havePic;
    private String[] pictures;//储存选项的图片*/
    //private Map<Integer,String> logic;//可能会存在一个问题是另外问题的前提条件
    //private boolean isNeed;
}
