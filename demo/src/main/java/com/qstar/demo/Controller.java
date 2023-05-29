package com.qstar.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import com.qstar.demo.Service.Handle;
import com.qstar.demo.pojo.*;
import com.qstar.demo.pojo.Receiver.CreatedReceive;
import com.qstar.demo.pojo.Receiver.FileReceive;
import com.qstar.demo.pojo.Receiver.QuestionaireReceive;
import com.qstar.demo.pojo.Receiver.SaveFillReceive;
import com.qstar.demo.pojo.Result.Result;
import com.qstar.demo.pojo.Result.ResultForCheck;
import com.qstar.demo.pojo.Result.ResultForFill;
import com.qstar.demo.pojo.Result.StatisticsResult;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import com.qstar.demo.Dao.Link;
@RestController
@CrossOrigin
public class Controller {
    @Autowired
    private Handle handle;
    @Autowired
    Link link;
    @RequestMapping("/hello")
    public String handle01(){
        return "helloworld!";
    }
    //获取已经用户已经创建的问卷，用于MyQ.vue
    @GetMapping("/getCreated")
    public Result getCreated(@RequestHeader("token") String token){
        System.out.println("要获取问卷的token:"+token);
        List<QuestionaireInfo> infos=handle.getCreated(token);
        return Result.success(infos);
    }
    //创建问卷
    @PostMapping("/create")
    public Result create(@RequestBody CreatedReceive receive, @RequestHeader("token") String token) throws IOException {
        String attach=handleFile(receive);
        int id=handle.create(receive.getTitle(),receive.getDescription(),attach,receive.getList(),token);
        return Result.success(id);
    }
    //查看已经创建问卷的详细信息
    @GetMapping("/check")
    public Result check(Integer id,@RequestHeader("token") String token){
        ResultForCheck result=handle.check(id,token);
        if(result!=null) {
            return Result.success(result);
        }
        return Result.fail("问卷id有误！");
    }
    //保存问卷
    @PostMapping("/save")
    public Result save(@RequestBody QuestionaireReceive receive, @RequestHeader("token") String token) throws IOException {
        if(handle.checkID(receive.getId(),token)) {
            String attach = handleFile(receive);
            boolean b = handle.save(receive.getId(), receive.getDescription(), attach, receive.getList(), token);
            return b ? Result.success() : Result.fail("已提交，无法再修改!");
        }else{
            return Result.fail("问卷id有误！");
        }
    }
    //提交问卷
    @PostMapping("/commit")
    public Result commit(@RequestBody QuestionaireReceive receive,@RequestHeader("token") String token) throws IOException {
        if(handle.checkID(receive.getId(),token)) {
            String attach = handleFile(receive);
            boolean b = handle.save(receive.getId(), receive.getDescription(), attach, receive.getList(), token);
            if (b) {
                handle.commit(receive.getId(), token);
                return Result.success();
            }
            return Result.fail("已提交!无需再提交");
        }else{
            return Result.fail("问卷id有误！");
        }
    }

    public String handleFile(FileReceive receive) throws IOException {
        if(receive.getFiles()!=null) {
            handle.handleFiles(receive.getList(), receive.getFiles());
        }
        if(receive.getAttach()!=null){
            return handle.handleFile(receive.getAttach());
        }
        return null;
    }
    public String handleFile(SaveFillReceive receive) throws IOException{
        if(receive.getFiles()!=null) {
            handle.handleFiles(receive.getData(), receive.getFiles());
        }
        if(receive.getAttach()!=null){
            return handle.handleFile(receive.getAttach());
        }
        return null;
    }


    //返回要填写的问卷的信息
    @GetMapping("/fill")
    public Result fill(Integer id,String email,@RequestHeader("token") String token) throws IOException {
        ResultForCheck list=handle.fill(id,email,token);
        if(list==null){
            return Result.fail("问卷id或者作者邮箱错误或访问的问卷未提交！");
        }
        return Result.success(list);
    }
    //返回已经填写的问卷的信息
    @GetMapping("/fillRecord")
    public Result getFillRecord(@RequestHeader("token") String token){//获取填写记录
        return Result.success(handle.getFillRecord(token));
    }
    //保存已经填写的数据
    @PostMapping("/saveFill")   //可能有文件传入
    public Result saveFill(@RequestBody SaveFillReceive receive,@RequestHeader("token") String token) throws IOException {
        String attach=handleFile(receive);
        Set<Integer> set;
        if(receive.getFiles()!=null){
            set=receive.getFiles().keySet();
        }else{
            set=null;
        }
        boolean b=handle.saveFill(receive.getId(),receive.getEmail(),receive.getData(),set,attach,token);
        return b?Result.success():Result.fail("问卷id或者作者邮箱错误!");
    }
    //查看已经填写过的问卷
    @GetMapping("/checkFill")
    public Result checkFill(Integer id,String email,@RequestHeader("token") String token) throws IOException {
        ResultForFill result=handle.checkFill(id,email,token);
        if(result!=null) {
            return Result.success(result);
        }
        return Result.fail("问卷id或者作者邮箱错误!");
    }
    //提交填写
    @PostMapping("/commitFill")
    public Result commitFill(@RequestBody SaveFillReceive receive,@RequestHeader("token") String token) throws IOException {
        String attach=handleFile(receive);
        Set<Integer> set;
        if(receive.getFiles()!=null){
            set=receive.getFiles().keySet();
        }else{
            set=null;
        }
        boolean b=handle.saveFill(receive.getId(),receive.getEmail(),receive.getData(),set,attach,token);
        if(!b){
            return Result.fail("问卷id或者作者邮箱错误!");
        }
        return handle.commitFill(receive.getId(),receive.getEmail(),token)?Result.success():Result.fail("已提交!");
    }
    //获取数据
    @GetMapping("/statistics/{index}")
    public Result statistics(@PathVariable Integer index,Integer id,@RequestHeader("token") String token){
        StatisticsResult result=handle.statistics(index,id,token);
        if(result!=null){
            return Result.success(result);
        }else{
            return Result.fail("问卷id错误或者问题序号超出范围");
        }
    }
}