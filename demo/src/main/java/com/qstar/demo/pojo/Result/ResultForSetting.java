package com.qstar.demo.pojo.Result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ResultForSetting {
    private boolean multiCommit;            //是否允许多次提交
    private boolean recordFillerName;          //是否记录名字
    private String begin;                 //开始时间
    private String end;                   //结束时间
}
