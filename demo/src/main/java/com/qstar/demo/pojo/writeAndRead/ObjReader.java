package com.qstar.demo.pojo.writeAndRead;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.qstar.demo.pojo.Question;
import com.qstar.demo.pojo.Questionaire;
import com.qstar.demo.pojo.User;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.Arrays;

@Component
public class ObjReader {//读取user对象，根据用户的名字读取，用户名就是文件名，储存在txt中
    @Value("${store.base}")
    private String base;
    @Value("${store.questionaireRoad}")
    private String questionaireRoad;
    @Value("${store.userRoad}")
    private String userRoad;
    private ObjectMapper objectMapper = new ObjectMapper();
    public String read(String name) throws IOException {
        //File file=new File(base+"/"+name+".txt");   //储存user对象的文件是txt格式的
        System.out.println("读取路径："+base+"/"+name+".txt");
        FileInputStream fis = new FileInputStream(base+"/"+name+".txt");
        InputStreamReader  isr = new InputStreamReader(fis, "utf-8");
        BufferedReader bf = new BufferedReader(isr);
        String objstr = "";
        String temp = "";
        temp = bf.readLine();
        while(temp != null){
            objstr += temp;
            temp = bf.readLine();
        }
        return objstr;
        // if(file.exists()) {//先检验文件是否存在，防止报错
        //     Reader r = new BufferedReader(new FileReader(file));
        //     String objstr = "";
        //     char[] c = new char[32];
        //     int len = 0;
        //     while ((len = r.read(c)) != -1) {
        //         objstr += new String(c, 0, len);
        //     }
        //     r.close();
        //     return objstr;
        // }else{
        //     return null;
        // }
    }
    public String read(String father,String name) throws IOException {
        return this.read(father+"/"+name);
    }
    public Questionaire readQuestionaire(int id) throws IOException {
        //return  (Questionaire) JSON.parseObject((String) read(questionaireRoad,id+""), Questionaire.class);
        return objectMapper.readValue(read(questionaireRoad,id+""),Questionaire.class);
    }
    public User readUser(String email) throws IOException {
        //return (User) JSON.parseObject((String) read(userRoad,email), User.class);
        return objectMapper.readValue(read(userRoad,email),User.class);
    }
}
