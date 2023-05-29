package com.qstar.demo.pojo.writeAndRead;

import com.alibaba.fastjson.JSON;
import com.qstar.demo.pojo.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ObjWriter {//把对象储存，需要储存的应该就User对象
    //应该一个问卷储存一个User对象
    private String base;
    public ObjWriter(String base){
        this.base=base;
    }
    public void write(User user,String subroad) throws IOException {//用邮箱作为文件名
        Writer w=new BufferedWriter(new FileWriter(base+"/"+subroad+"/"+user.get_email()+".txt"));
        w.write(JSON.toJSONString(user));
        w.close();
    }
}
