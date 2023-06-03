package com.qstar.demo.Service;

import com.qstar.demo.Dao.Link;
import com.qstar.demo.pojo.*;
import com.qstar.demo.pojo.Receiver.QuestionMiniUnit;
import com.qstar.demo.pojo.Receiver.SaveEditReceive;
import com.qstar.demo.pojo.Receiver.SettingReceive;
import com.qstar.demo.pojo.Result.Result;
import com.qstar.demo.pojo.Result.ResultForCheck;
import com.qstar.demo.pojo.Result.ResultForFill;
import com.qstar.demo.pojo.Result.StatisticsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class Handle {
    @Autowired
    Link link;
    public List<QuestionaireInfo> getCreated(String token) throws IOException {
        return link.getCreated(token);
    }
    public int create(String title, String description/*,String filename*/,List<Question> questions,String token,boolean commit) throws IOException {
         return link.create(title,description,questions,token,commit);
    }
    public ResultForCheck check(int id, String token) throws IOException {
        return link.check(id,token);
    }
    public Result save(int id ,String title,String description/*,String filename*/,List<Question> list,String token,boolean commit) throws IOException {
        return link.save(id,title,description,list,token,commit);
    }
    /*public Result commit(int id,String token) throws IOException {
        return link.commit(id,token);
    }*/
    public Result fill(int id,String token,boolean commit) throws IOException{
        return link.fill(id,token,commit);
    }
    public List<FilledQuestionaireInfo> getFillRecord(String token){
        return link.getFillRecord(token);
    }
    public Result saveFill(int id,String[] data, String token,boolean commit) throws IOException {
        return link.saveFill(id,data,token,commit);
    }
    public ResultForFill checkFill(int id, String token) throws IOException{
        return link.checkFill(id,token);
    }
    /*public Result commitFill(int id, String token) throws IOException {
        return link.commitFill(id,token);
    }*/
    public Result statistics(int index, int id, String token) throws IOException {
        return link.statistics(index,id,token);
    }


    /*public void handleFiles(List<Question> questions, Map<Integer,List<MultipartFile>> files) throws IOException {//用于设置每个问题对象中文件名的数组
        Set<Integer> set=files.keySet();
        for(Integer i:set){//还得删除旧的文件
            Question question =questions.get(i);
            link.deleteFiles(question.getPictures());
            question.setPictures(link.storeFiles(files.get(i)));
            question.setHavePic(true);
        }
    }*/
    /*public void handleFiles(String[] data, Map<Integer,MultipartFile> files) throws IOException {
        Set<Integer> set=files.keySet();
        for(Integer i:set){//还得删除旧的文件
            data[i]=link.storeFile(files.get(i),true);
        }
    }
    public String handleFile(MultipartFile file) throws IOException {//专门用于处理附件文件的
        return link.storeFile(file,false);      //总路径
    }*/
    public boolean checkID(int id,String token){//检验id是否正确
        return link.checkID(id,token);
    }
    public Result setting(boolean recordName,boolean multiCommit,Date begin,Date end,Integer id,String token) throws IOException {
        return link.setting(recordName,multiCommit,begin,end,id,token);
    }
    public Result getSetting(Integer id,String token) throws IOException {
        return link.getSetting(id,token);
    }
    public Result authorizeCheck(Integer id,String name,String email,String photo,String token) throws IOException {
        return link.authorizeCheck(id,name,email,photo,token);
    }
    public Result authorizeEdit(Integer id,String name,String email,String photo,String token) throws IOException {
        return link.authorizeEdit(id,name,email,photo,token);
    }
    public Result getAllowCheck(String token) throws IOException {
        return link.getAllowCheck(token);
    }
    public Result getAllowEdit(String token) throws IOException {
        return link.getAllowEdit(token);
    }
    public Result saveEdit(int id,String title, String description, QuestionMiniUnit[] unit, String token) throws IOException {
        return link.saveEdit(id,title,description,unit,token);
    }
    public Result delete(Integer id,String token){
        return link.delete(id,token);
    }
    public Result withdraw(Integer id,String token) throws IOException {
        return link.withdraw(id,token);
    }

    public Result authorizeManage(Integer id, String name, String email, String photo, String token) throws IOException {
        return link.authorizeManage(id,name,email,photo,token);
    }
}
