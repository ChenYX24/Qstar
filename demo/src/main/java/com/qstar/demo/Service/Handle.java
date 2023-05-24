package com.wenjuan.wenjuandemo.Service;

import com.wenjuan.wenjuandemo.Dao.Link;
import com.wenjuan.wenjuandemo.pojo.*;
import com.wenjuan.wenjuandemo.pojo.Result.ResultForCheck;
import com.wenjuan.wenjuandemo.pojo.Result.ResultForFill;
import com.wenjuan.wenjuandemo.pojo.Result.StatisticsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class Handle {
    @Autowired
    Link link;
    public List<QuestionaireInfo> getCreated(String token){
        return link.getCreated(token);
    }
    public int create(String title, String description,String filename,List<Question> questions,String token){
         return link.create(title,description,filename,questions,token);
    }
    public ResultForCheck check(int id, String token){
        return link.check(id,token);
    }
    public boolean save(int id ,String description,String filename,List<Question> list,String token){
        return link.save(id,description,filename,list,token);
    }
    public boolean commit(int id,String token){
        return link.commit(id,token);
    }
    public ResultForCheck fill(int id,String creator,String token) throws IOException{
        return link.fill(id,creator,token);
    }
    public List<FilledQuestionaireInfo> getFillRecord(String token){
        return link.getFillRecord(token);
    }
    public boolean saveFill(int id,String creator,String[] data,Set<Integer> set,String attach, String token){
        return link.saveFill(id,creator,data,set,attach,token);
    }
    public ResultForFill checkFill(int id, String creator, String token) throws IOException{
        return link.checkFill(id,creator,token);
    }
    public boolean commitFill(int id, String creator, String token){
        return link.commitFill(id,creator,token);
    }
    public StatisticsResult statistics(int index, int id, String token){
        return link.statistics(index,id,token);
    }


    public void handleFiles(List<Question> questions, Map<Integer,List<MultipartFile>> files) throws IOException {//用于设置每个问题对象中文件名的数组
        Set<Integer> set=files.keySet();
        for(Integer i:set){//还得删除旧的文件
            Question question =questions.get(i);
            link.deleteFiles(question.getPictures());
            question.setPictures(link.storeFiles(files.get(i)));
            question.setHavePic(true);
        }
    }
    public void handleFiles(String[] data, Map<Integer,MultipartFile> files) throws IOException {
        Set<Integer> set=files.keySet();
        for(Integer i:set){//还得删除旧的文件
            data[i]=link.storeFile(files.get(i),true);
        }
    }
    public String handleFile(MultipartFile file) throws IOException {//专门用于处理附件文件的
        return link.storeFile(file,false);      //总路径
    }
    public boolean checkID(int id,String token){//检验id是否正确
        return link.checkID(id,token);
    }
}
