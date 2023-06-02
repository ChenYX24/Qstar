package com.qstar.demo.pojo.writeAndRead;

import com.alibaba.fastjson.JSON;
import com.qstar.demo.pojo.Questionaire;
import com.qstar.demo.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@Component
public class ObjWriter {//把对象储存，需要储存的应该就User对象
    //应该一个问卷储存一个User对象
    @Value("${store.base}")
    private String base;
    @Value("${store.questionaireRoad}")
    private String questionaireRoad;
    @Value("${store.userRoad}")
    private String userRoad;

    public void writeUser(User user) throws IOException {//用邮箱作为文件名
        Writer w=new BufferedWriter(new FileWriter(base+"/"+userRoad+"/"+user.getEmail()+".txt"));
        w.write(JSON.toJSONString(user));
        w.close();
    }
    public void writeQuestionaire(Questionaire questionaire) throws IOException {
        Writer w=new BufferedWriter(new FileWriter(base+"/"+questionaireRoad+"/"+questionaire.getInfo().getId()+".txt"));
        w.write(JSON.toJSONString(questionaire));
        w.close();
    }
}
