package com.qstar.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Scanner;
@RestController
public class Controller {
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
    @RequestMapping("/test")
    public void test(){
        // EmailSender emailSender = new EmailSender();
        // String test = emailSender.sendcode("1835208239@qq.com");
        // System.out.println(test);
    }
    @RequestMapping("/get")
    public void getuser(){
        Runnable task = () -> {
            try (Scanner sc = new Scanner(System.in)){
                String result = " ";
                System.out.println("   要使用id查询还是邮箱查询");
                System.out.println("id查询: 0         邮箱查询: 1");
                int flag = sc.nextInt();
                if(flag == 0){
                    System.out.println("请输入要查询的id");
                    int id = sc.nextInt();
                    result = userService.GetUserById(id);
                    System.out.println(result);
                }
                else if(flag == 1){
                    System.out.println("请输入要查询的邮箱");
                    sc.nextLine();//把缓冲区里的回车吞掉
                    String Email = sc.nextLine();
                    result = userService.GetUserByEmail(Email);
                    System.out.println(result);
                }
                else{
                    System.out.println("error!!!");
                }
                sc.nextLine();
                sc.close();
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };
        new Thread(task).start();
    }
    @RequestMapping("/change")
    public void changepasswd(){
        Runnable task = () -> {
            try(Scanner sc = new Scanner(System.in);){
                System.out.println("请输入你的邮箱");
                String Email = sc.nextLine();
                System.out.println("请输入新的密码");
                String Passwd = sc.nextLine();
                userService.ChangePasswdByEmail(Email, Passwd);
                sc.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        };
        new Thread(task).start();
    }
}