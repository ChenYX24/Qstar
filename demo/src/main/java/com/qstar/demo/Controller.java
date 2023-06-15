package com.qstar.demo;

import com.qstar.demo.pojo.Receiver.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.qstar.demo.Service.Handle;
import com.qstar.demo.pojo.*;
import com.qstar.demo.pojo.Result.Result;
import com.qstar.demo.pojo.Result.ResultForCheck;
import com.qstar.demo.pojo.Result.ResultForFill;
import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.IOException;
import java.util.List;
import com.qstar.demo.Dao.Link;
@RestController
@CrossOrigin
public class Controller {
    public String totoken(String token){
      System.out.println("传入的token:" + token);
        String temp = userService.userio.getKeyValueofJson(token, "data");
        if(temp != ""){
         System.out.println("data:" + temp);
            return temp;
        }
        System.out.println("无修改");
        return token;
    }
    @Autowired
    private Handle handle;
    @Autowired
    Link link;
    @Autowired
    UserService userService;
    @RequestMapping("/hello")
    public String handle01(){
        return "helloworld!";
    }
    //获取已经用户已经创建的问卷，用于MyQ.vue
    @GetMapping("/getCreated")
    public Result getCreated(@RequestHeader("token") String token) throws IOException {
        token = totoken(token);
        System.out.println("要获取问卷的token:"+token);
        List<QuestionaireInfo> infos=handle.getCreated(token);
        System.out.println("success");
        return Result.success(infos);
    }
    //创建问卷
    @RequestMapping("/create")
    public Result create(@RequestBody CreatedReceive receive, @RequestHeader("token") String token) throws IOException {
        /*String attach=handleFile(receive);*/
        token = totoken(token);
        System.out.println("title:"+receive.getTitle());
        System.out.println("content:"+receive.getContent());
        //System.out.println("receive:"+receive);
        System.out.println(token);
        System.out.println(receive.isCommit());
        return handle.create(receive.getTitle(),receive.getDescription(),receive.getContent(),token,receive.isCommit());
    }
    //查看已经创建问卷的详细信息
    @RequestMapping("/check")
    public Result check(@RequestHeader("token") String token,@RequestBody String info) throws IOException {
        token = totoken(token);
        System.out.println(info);
        String temp = userService.userio.getKeyValueofJson(info, "id");
        int id = Integer.parseInt(temp);
        System.out.println("id:" + id);
        //int Id = Integer.parseInt(id);
        ResultForCheck result=handle.check(id,token);
        if(result!=null) {
            System.out.println("check success");
            return Result.success(result);
        }
        return Result.fail("问卷id有误！");
    }
    //保存问卷
    @PostMapping("/save")
    public Result save(@RequestBody QuestionaireReceive receive, @RequestHeader("token") String token) throws IOException {
        token = totoken(token);
        return handle.save(receive.getId(),receive.getTitle(),receive.getDescription(),receive.getContent(),token,receive.isCommit());
    }
    //提交问卷
    /*@PostMapping("/commit")
    public Result commit(Integer id,@RequestHeader("token") String token) throws IOException {//直接提交
            return handle.commit(id,token);
    }*/

    /*public String handleFile(FileReceive receive) throws IOException {
        if(receive.getFiles()!=null) {
            handle.handleFiles(receive.getList(), receive.getFiles());
        }
        if(receive.getAttach()!=null){
            return handle.handleFile(receive.getAttach());
        }
        return null;
    }*/
    /*public String handleFile(SaveFillReceive receive) throws IOException{
        if(receive.getFiles()!=null) {
            handle.handleFiles(receive.getData(), receive.getFiles());
        }
        if(receive.getAttach()!=null){
            return handle.handleFile(receive.getAttach());
        }
        return null;
    }*/


    //返回要填写的问卷的信息
    @Data
    static class fillRecive{
        int id;
        boolean commit;
    }
    @RequestMapping("/fill")
    //public Result fill(Integer id,boolean commit,@RequestHeader("token") String token) throws IOException {
    public Result fill(@RequestBody String info,@RequestHeader("token") String token) throws IOException {
        token = totoken(token);
        System.out.println(info);
        fillRecive fill = JSON.parseObject(info,fillRecive.class);
        return handle.fill(fill.getId(),token,fill.isCommit());
        //return Result.success();
    }
    //返回已经填写的问卷的信息
    @GetMapping("/fillRecord")
    public Result getFillRecord(@RequestHeader("token") String token) throws IOException {//获取填写记录
        token = totoken(token);
        System.out.println("要获取填写问卷的token:" + token);
        return Result.success(handle.getFillRecord(token));
    }
    //保存已经填写的数据
    @PostMapping("/saveFill")
    public Result saveFill(@RequestBody SaveFillReceive receive,@RequestHeader("token") String token) throws IOException {
        token = totoken(token);
        /*String attach=handleFile(receive);*/
        /*Set<Integer> set;
        if(receive.getFiles()!=null){
            set=receive.getFiles().keySet();
        }else{
            set=null;
        }*/
        System.out.println(receive);
        return handle.saveFill(receive.getFilledID(),receive.getId(), receive.getData(), token, receive.isCommit());
    }
    //查看已经填写过的问卷
    @RequestMapping("/checkFill")
    public Result checkFill(@RequestBody String info,@RequestHeader("token") String token) throws IOException {
        token = totoken(token);
        int id = Integer.parseInt(userService.userio.getKeyValueofJson(info, "id"));
        //System.out.println(token);
        ResultForFill result=handle.checkFill(id,token);
        if(result!=null) {
            return Result.success(result);
        }
        return Result.fail("填写记录的ID错误!");
    }
    //提交填写
    /*@PostMapping("/commitFill")
    public Result commitFill(Integer id,@RequestHeader("token") String token) throws IOException {
        return handle.commitFill(id,token);
    }*/
    //获取数据
    @RequestMapping("/statistics")
    public Result statistics(@RequestBody String info,@RequestHeader("token") String token) throws IOException {
        token = totoken(token);
        int id = Integer.parseInt(userService.userio.getKeyValueofJson(info, "id"));
        return handle.statistics(id,token);
    }
    @GetMapping("/getFilled")
    public Result getFilled(Integer id,@RequestHeader("token") String token) throws IOException {//用于获取这个问卷的所有填写记录
        token = totoken(token);
        return handle.getFilled(id,token);
    }
    @PostMapping("/setting")
    public Result setting(@RequestBody SettingReceive receive,@RequestHeader("token") String token) throws IOException {//设置问卷的配置信息
        token = totoken(token);
        Result result= handle.setting(receive.isRecordName(),receive.isMultiCommit(),receive.getBegin(),receive.getEnd(),receive.getId(),token);
        if(result.getCode()==1){//如果前面的没问题，不需要直接返回
            switch (receive.getPeople().getAuthority()){
                case 0:
                    result=authorizeManage(receive.getId(),receive.getPeople().getName(),receive.getPeople().getEmail(),receive.getPeople().getPhoto(),token);
                    break;
                case 1:
                    result=authorizeEdit(receive.getId(),receive.getPeople().getName(),receive.getPeople().getEmail(),receive.getPeople().getPhoto(),token);
                    break;
                case 2:
                    result=authorizeCheck(receive.getId(),receive.getPeople().getName(),receive.getPeople().getEmail(),receive.getPeople().getPhoto(),token);
                    break;
            }
        }
        return result;
    }
    @GetMapping("/getSetting")
    public Result getSetting(Integer id,@RequestHeader("token") String token) throws IOException {//获取问卷配置信息
        token = totoken(token);
        return handle.getSetting(id,token);
    }
    @RequestMapping("/getPage")
    public Result getPage(@RequestBody String info){//这是给其他用户权限的前置操作，用于确认是否是自己想要邀请的用户
        //token = totoken(token);
        //到时候直接调用cjd的接口
        //可能会邮箱获取失败，到时候返回fail即可
        System.out.println(info);
        Userinfo userinfo = userService.getUserinfo(info);
        return Result.success(userinfo);
        //return null;
    }
    public Result authorizeManage(Integer id,String name,String email,String photo,String token) throws IOException {//授权用户查看问卷的权限
        token = totoken(token);
        return handle.authorizeManage(id,name,email,photo,token);                         //token交给拦截器，这里不需要
    }
    public Result authorizeCheck(Integer id,String name,String email,String photo,String token) throws IOException {//授权用户查看问卷的权限
        token = totoken(token);
        return handle.authorizeCheck(id,name,email,photo,token);                         //token交给拦截器，这里不需要
    }

    public Result authorizeEdit(Integer id,String name,String email,String photo,String token) throws IOException {//授权用户编辑的权限
        token = totoken(token);
        return handle.authorizeEdit(id,name,email,photo,token);
    }
    @GetMapping("/getAllowCheck")
    public Result getAllowCheck(@RequestHeader("token") String token) throws IOException {//获取允许查看的列表
        token = totoken(token);
        return handle.getAllowCheck(token);
    }
    @GetMapping("/getAllowEdit")
    public Result getAllowEdit(@RequestHeader("token") String token) throws IOException {//获取允许编辑的列表
        token = totoken(token);
        return handle.getAllowEdit(token);
    }
    @PostMapping("/saveEdit")
    public Result saveEdit(@RequestBody SaveEditReceive receive,@RequestHeader("token") String token) throws IOException {//有保留地编辑
        token = totoken(token);
        return handle.saveEdit(receive.getId(), receive.getTitle(), receive.getDescription(), receive.getQuestions(), token);
    }
    @GetMapping("/delete")
    public Result delete(Integer id,@RequestHeader("token") String token){//删除问卷
        token = totoken(token);
        return handle.delete(id,token);
    }
    @GetMapping("/withdraw")
    public Result withdraw(Integer id,@RequestHeader("token") String token) throws IOException {//撤回填写数据
        token = totoken(token);
        return link.withdraw(id,token);
    }

}