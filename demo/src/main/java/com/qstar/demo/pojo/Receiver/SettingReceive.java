package com.qstar.demo.pojo.Receiver;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SettingReceive {//设置
    private Integer id;     //问卷id
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm",timezone = "GMT+8")
    private Date begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm",timezone = "GMT+8")
    private Date end;
    private boolean recordName; //是否记录名字
    private boolean multiCommit;    //是否重复提交
    private AuthorityReceive people;
}
