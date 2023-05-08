package com.qstar.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Scanner;
@RestController
public class Controller {
    @Autowired
    UserService userService;
    @RequestMapping("/hello")
    public String handle01(){
        return "helloworld!";
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