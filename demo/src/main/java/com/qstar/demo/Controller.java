package com.qstar.demo;

import com.qstar.demo.pojo.Receiver.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import com.qstar.demo.Service.Handle;
import com.qstar.demo.pojo.*;
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
    public Result check(Integer id,@RequestHeader("token") String token) throws IOException {
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
            boolean b = handle.save(receive.getId(), receive.getTitle(),receive.getDescription(), attach, receive.getList(), token);
            return b ? Result.success() : Result.fail("已提交，无法再修改!");
        }else{
            return Result.fail("问卷id有误！");
        }
    }
    //提交问卷
    @PostMapping("/commit")
    public Result commit(Integer id,@RequestHeader("token") String token) throws IOException {//直接提交
            return handle.commit(id,token);
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
    public Result fill(Integer id,@RequestHeader("token") String token) throws IOException {
        return handle.fill(id,token);
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
        boolean b=handle.saveFill(receive.getFilledID(),receive.getData(),set,attach,token);
        return b?Result.success():Result.fail("填写记录的ID错误!");
    }
    //查看已经填写过的问卷
    @GetMapping("/checkFill")
    public Result checkFill(Integer id,@RequestHeader("token") String token) throws IOException {
        ResultForFill result=handle.checkFill(id,token);
        if(result!=null) {
            return Result.success(result);
        }
        return Result.fail("填写记录的ID错误!");
    }
    //提交填写
    @PostMapping("/commitFill")
    public Result commitFill(Integer id,@RequestHeader("token") String token) throws IOException {
        return handle.commitFill(id,token);
    }
    //获取数据
    @GetMapping("/statistics/{index}")
    public Result statistics(@PathVariable Integer index,Integer id,@RequestHeader("token") String token) throws IOException {
        return handle.statistics(index,id,token);
    }

    @PostMapping("/setting")
    public Result setting(@RequestBody SettingReceive receive,@RequestHeader("token") String token) throws IOException {//设置问卷的配置信息
        return handle.setting(receive.isRecordName(),receive.isMultiCommit(),receive.getBegin(),receive.getEnd(),receive.getId(),token);
    }
    @GetMapping("/getSetting")
    public Result getSetting(Integer id,@RequestHeader("token") String token) throws IOException {//获取问卷配置信息
        return handle.getSetting(id,token);
    }
    @GetMapping("/getPage")
    public Result getPage(String email,Integer id,@RequestHeader("token")String token){//这是给其他用户权限的前置操作，用于确认是否是自己想要邀请的用户
        //到时候直接调用cjd的接口
        //可能会邮箱获取失败，到时候返回fail即可
        return null;
    }
    @PostMapping("/authorizeCheck")
    public Result authorizeCheck(Integer id,String email,@RequestHeader("token") String token) throws IOException {//授权用户查看问卷的权限
        return handle.authorizeCheck(id,email,token);                         //token交给拦截器，这里不需要
    }
    @PostMapping("/authorizeEdit")
    public Result authorizeEdit(Integer id,String email,@RequestHeader("token") String token) throws IOException {//授权用户编辑的权限
        return handle.authorizeEdit(id,email,token);
    }
    @GetMapping("/getAllowCheck")
    public Result getAllowCheck(@RequestHeader("token") String token) throws IOException {//获取允许查看的列表
        return handle.getAllowCheck(token);
    }
    @GetMapping("/getAllowEdit")
    public Result getAllowEdit(@RequestHeader("token") String token) throws IOException {//获取允许编辑的列表
        return handle.getAllowEdit(token);
    }
    @PostMapping("/saveEdit")
    public Result saveEdit(@RequestBody SaveEditReceive receive,@RequestHeader("token") String token) throws IOException {//有保留地编辑
        return handle.saveEdit(receive.getId(), receive.getTitle(), receive.getDescription(), receive.getQuestions(), token);
    }
    @GetMapping("/delete")
    public Result delete(Integer id,@RequestHeader("token") String token){//删除问卷
        return handle.delete(id,token);
    }
    @GetMapping("/withdraw")
    public Result withdraw(Integer id,@RequestHeader("token") String token) throws IOException {//撤回填写数据
        return link.withdraw(id,token);
    }

}