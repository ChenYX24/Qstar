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
import java.util.Scanner;
import java.io.IOException;
import java.util.List;
import java.util.Set;
@RestController
@CrossOrigin
public class Controller {
    @Autowired
    private Handle handle;
    @Autowired
    EmailSender emailSender;
    @Autowired
    UserService userService;
    @Autowired
    DataIO userio;
    @RequestMapping("/hello")
    public String handle01(){
        return "helloworld!";
    }
    //登录功能，解析前端输入的json，判断邮箱与密码是否对应，返回值为该用户的json或者为空格。
    @RequestMapping("/login")
    public String login(@RequestBody String json){
        System.out.println(json);
        String result = " ";
        String email = "";
        String password = "";
        email = userio.getKeyValueofJson(json, "email");
        password = userio.getKeyValueofJson(json, "password");
        if(userService.MatchEmailtoPasswd(email, password)){
            result = userService.GetUserByEmail(email);
            System.out.println(result);
        }
        return result;
    }
    @RequestMapping("/register")
    public String saveuser(@RequestBody String json){
        System.out.println(json);
        String result = " ";
        String username = " ";
        String email = " ";
        String password = " ";
        username = userio.getKeyValueofJson(json, "username");
        email = userio.getKeyValueofJson(json, "email");
        System.out.println(email);
        password = userio.getKeyValueofJson(json, "password");
        User user = new User(username, email, password);
        userService.SaveUser(user);
        result = userService.GetUserByEmail(email);
        return result;
    }
    @RequestMapping("/sendcode")
    public String sendcode(@RequestBody String json){
        System.out.println(json);
        String success = "false";
        String email = userio.getKeyValueofJson(json, "email");
        emailSender.sendcode(email);
        success = "true";
        return success;
    }
    @RequestMapping("/checkcode")
    public String checkcode(@RequestBody String json){
        System.out.println(json);
        String success = "false";
        String email = userio.getKeyValueofJson(json, "email");
        String code = userio.getKeyValueofJson(json, "code");
        success = emailSender.checkcode(email, code);
        System.out.println(success);
        return success;
    }
    @RequestMapping("/changepasswd")
    public String changepasswd(@RequestBody String json){
        String success = "false";
        String passwd = userio.getKeyValueofJson(json, "password");
        String email = userio.getKeyValueofJson(json, "email");
        userService.ChangePasswdByEmail(email, passwd);
        String userjson = userService.GetUserByEmail(email);
        if(userio.getKeyValueofJson(userjson, "_passwd").hashCode() == passwd.hashCode()){
            success = "true";
        }
        return success;
    }
    @RequestMapping("/autologin")
    public String autologin(@RequestBody String request) {
        String json = userio.getKeyValueofJson(request, "token");
        String result = " ";
        String email = "";
        String password = "";
        email = userio.getKeyValueofJson(json, "_email");
        password = userio.getKeyValueofJson(json, "_passwd");
        if(userService.MatchEmailtoPasswd(email, password)){
            result = userService.GetUserByEmail(email);
            System.out.println(result);
        }
        return result;
    }
    @RequestMapping("/test")
    public String test(@RequestBody String request) {
        String json = userio.getKeyValueofJson(request, "token");
        String result = " ";
        String email = "";
        String password = "";
        email = userio.getKeyValueofJson(json, "_email");
        password = userio.getKeyValueofJson(json, "_passwd");
        if(userService.MatchEmailtoPasswd(email, password)){
            result = userService.GetUserByEmail(email);
            System.out.println(result);
        }
        return result;
        // String json = request.get("token");
        // ObjectMapper mapper = new ObjectMapper();
        // try {
        //     Token token = mapper.readValue(json, Token.class);
        //     System.out.println(token.get_id());
        //     // do something with the token object
        //     return json;
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     return "Error processing request";
        // }
    }
    // @RequestMapping("/get")
    // public void getuser(){
    //     Runnable task = () -> {
    //         try (Scanner sc = new Scanner(System.in)){
    //             String result = " ";
    //             System.out.println("   要使用id查询还是邮箱查询");
    //             System.out.println("id查询: 0         邮箱查询: 1");
    //             int flag = sc.nextInt();
    //             if(flag == 0){
    //                 System.out.println("请输入要查询的id");
    //                 int id = sc.nextInt();
    //                 result = userService.GetUserById(id);
    //                 System.out.println(result);
    //             }
    //             else if(flag == 1){
    //                 System.out.println("请输入要查询的邮箱");
    //                 sc.nextLine();//把缓冲区里的回车吞掉
    //                 String Email = sc.nextLine();
    //                 result = userService.GetUserByEmail(Email);
    //                 System.out.println(result);
    //             }
    //             else{
    //                 System.out.println("error!!!");
    //             }
    //             sc.nextLine();
    //             sc.close();
    //             return;
    //         } catch (Exception e) {
    //             System.out.println(e.getMessage());
    //         }
    //     };
    //     new Thread(task).start();
    // }
    // @RequestMapping("/change")
    // public void changepasswd(){
    //     Runnable task = () -> {
    //         try(Scanner sc = new Scanner(System.in);){
    //             System.out.println("请输入你的邮箱");
    //             String Email = sc.nextLine();
    //             System.out.println("请输入新的密码");
    //             String Passwd = sc.nextLine();
    //             userService.ChangePasswdByEmail(Email, Passwd);
    //             sc.close();
    //         }
    //         catch(Exception e){
    //             System.out.println(e.getMessage());
    //         }
    //     };
    //     new Thread(task).start();
    // }
    @GetMapping("/getCreated")
    public Result getCreated(@RequestHeader("token") String token){
        List<QuestionaireInfo> infos=handle.getCreated(token);
        return Result.success(infos);
    }
    @PostMapping("/create")
    public Result create(@RequestBody CreatedReceive receive, @RequestHeader("token") String token) throws IOException {
        String attach=handleFile(receive);
        int id=handle.create(receive.getTitle(),receive.getDescription(),attach,receive.getList(),token);
        return Result.success(id);
    }
    @GetMapping("/check")
    public Result check(Integer id,@RequestHeader("token") String token){
        ResultForCheck result=handle.check(id,token);
        if(result!=null) {
            return Result.success(result);
        }
        return Result.fail("问卷id有误！");
    }
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



    @GetMapping("/fill")
    public Result fill(Integer id,String creator,@RequestHeader("token") String token) throws IOException {
        ResultForCheck list=handle.fill(id,creator,token);
        if(list==null){
            return Result.fail("问卷id或者作者名错误或访问的问卷未提交！");
        }
        return Result.success(list);
    }
    @GetMapping("/fillRecord")
    public Result getFillRecord(@RequestHeader("token") String token){//获取填写记录
        return Result.success(handle.getFillRecord(token));
    }
    @PostMapping("/saveFill")   //可能有文件传入
    public Result saveFill(@RequestBody SaveFillReceive receive,@RequestHeader("token") String token) throws IOException {
        String attach=handleFile(receive);
        Set<Integer> set;
        if(receive.getFiles()!=null){
            set=receive.getFiles().keySet();
        }else{
            set=null;
        }
        boolean b=handle.saveFill(receive.getId(),receive.getCreator(),receive.getData(),set,attach,token);
        return b?Result.success():Result.fail("问卷id或者作者名错误!");
    }
    @GetMapping("/checkFill")
    public Result checkFill(Integer id,String creator,@RequestHeader("token") String token) throws IOException {
        ResultForFill result=handle.checkFill(id,creator,token);
        if(result!=null) {
            return Result.success(result);
        }
        return Result.fail("问卷id或者作者名错误!");
    }
    @PostMapping("/commitFill")
    public Result commitFill(@RequestBody SaveFillReceive receive,@RequestHeader("token") String token) throws IOException {
        String attach=handleFile(receive);
        Set<Integer> set;
        if(receive.getFiles()!=null){
            set=receive.getFiles().keySet();
        }else{
            set=null;
        }
        boolean b=handle.saveFill(receive.getId(),receive.getCreator(),receive.getData(),set,attach,token);
        if(!b){
            return Result.fail("问卷id或者作者名错误!");
        }
        return handle.commitFill(receive.getId(),receive.getCreator(),token)?Result.success():Result.fail("已提交!");
    }
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