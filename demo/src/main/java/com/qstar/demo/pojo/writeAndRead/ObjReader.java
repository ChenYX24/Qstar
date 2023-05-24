package com.wenjuan.wenjuandemo.pojo.writeAndRead;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.wenjuan.wenjuandemo.pojo.User;
import org.springframework.asm.TypeReference;

import java.io.*;
import java.util.Arrays;

public class ObjReader {//读取user对象，根据用户的名字读取，用户名就是文件名，储存在txt中
    private String base;

    public ObjReader(String base) {//注意：这个base是储存的根目录，需要在最后加上\\
        this.base = base;
    }
    public User read(String name) throws IOException {
        File file=new File(base+"/"+name+".txt");   //储存user对象的文件是txt格式的
        if(file.exists()) {//先检验文件是否存在，防止报错
            Reader r = new BufferedReader(new FileReader(file));
            String objstr = "";
            char[] c = new char[32];
            int len = 0;
            while ((len = r.read(c)) != -1) {
                objstr += new String(c, 0, len);
            }
            r.close();
            return (User) JSON.parseObject(objstr, User.class);
        }else{
            return null;
        }
    }
    public User read(String father,String name) throws IOException {
        return this.read(father+"/"+name);
    }
}
