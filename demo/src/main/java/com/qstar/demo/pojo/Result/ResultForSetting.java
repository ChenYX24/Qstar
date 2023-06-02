package com.qstar.demo.pojo.Result;

import com.qstar.demo.pojo.Receiver.AuthorityReceive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResultForSetting {
    private String begin;                 //开始时间
    private String end;                   //结束时间
    private boolean multiCommit;            //是否允许多次提交
    private boolean recordFillerName;          //是否记录名字
    private List<AuthorityReceive> people;
}
