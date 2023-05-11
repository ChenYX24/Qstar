package com.qstar.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Scanner;
@RestController
public class Controller {
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
    @RequestMapping("/save")
    public void saveuser(){
        Runnable task = () -> {//定义线程执行内容
            try (Scanner sc = new Scanner(System.in)){
                User user = new User();
		        System.out.println("请输入用户名");
		        user.set_name(sc.nextLine());
		        System.out.println("请输入邮箱");
		        user.set_email(sc.nextLine());
		        System.out.println("请输入密码");
		        user.set_passwd(sc.nextLine());
		        sc.close();
                userService.SaveUser(user);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };
        new Thread(task).start();//启动线程
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