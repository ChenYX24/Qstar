package com.qstar.demo;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.qstar.demo.Dao.Link;
import com.qstar.demo.pojo.User;
import com.qstar.demo.pojo.writeAndRead.ObjReader;
import com.qstar.demo.pojo.Result.Result;
import com.qstar.demo.pojo.Userinfo;
@RestController
@CrossOrigin
public class LoginController {
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
    EmailSender emailSender;
    @Autowired
    UserService userService;
    @Autowired
    DataIO userio;
    @Autowired
    Link link;
     //登录功能，解析前端输入的json，判断邮箱与密码是否对应，返回值为该用户的json或者为空格。
     @RequestMapping("/login")
     public String login(@RequestBody String info){
        System.out.println("前端传入的json" + info);
         String result = " ";
         result = userService.login(info);
         return result;
     }
     //注册
     @RequestMapping("/register")
     public String saveuser(@RequestBody String info){
         System.out.println("前端传入的json" + info);
         String result = " ";
         result = userService.register(info);
         return result;
     }
     //发邮箱验证码
     @RequestMapping("/sendcode")
     public String sendcode(@RequestBody String info){
        System.out.println("前端传入的json" + info);
        String success = "false";
        success = userService.sendcode(info);
        return success;
     }
     //检验邮箱验证码是否正确
     @RequestMapping("/checkcode")
     public String checkcode(@RequestBody String info){
        System.out.println("前端传入的json" + info);
        String success = "false";
        success = userService.checkcode(info);
        return success;
     }
     //修改密码
     @RequestMapping("/changepasswd")
     public String changepasswd(@RequestHeader("token") String token,@RequestBody String json){
        token = totoken(token);
        System.out.println("前端传入的json" + json + " " + token);
        String success = "false";
        success = userService.ChangePasswd(token,json);
        return success;
     }
     //找回密码
     @RequestMapping("/findpasswd")
     public String findpasswd(@RequestBody String info){
         String success = "false";
         success = userService.FindPasswd(info);
         return success;
     }
     //自动登录
     @RequestMapping("/autologin")
     public String autologin(@RequestBody String info) {
        System.out.println("前端传入的json" + info);
        String result = " ";
        result = userService.autoLogin(info);
        return result;
     }
     //分享问卷
     @RequestMapping("/share")
     public String share(@RequestHeader("token") String token,@RequestBody String info) {
      token = totoken(token);
        String qrcode = "";
        qrcode = userService.Share(info);
        return qrcode;
     }
     //修改省份和电话号
     @RequestMapping("/changepl")
     //@RequestHeader("token") String token,@RequestBody String request
     public Result changepl(@RequestHeader("token") String token,@RequestBody String info) {
      token = totoken(token);
      System.out.println(info);
      if(userService.ChangPL(token, info)){
         return Result.success("true");
      }
      return Result.fail("false");
     }
     //修改用户名
     @RequestMapping("/changename")
     public Result changename(@RequestHeader("token") String token,@RequestBody String info) {
      token = totoken(token);
      System.out.println(info);
      if(userService.ChangeName(token, info)){
         return Result.success("true");
      }
      return Result.fail("false");
     }
     //是否已经登录
     @RequestMapping("/isLogin")
     public Result isLogin(@RequestHeader("token") String token) {
      token = totoken(token);
      if(userService.isLogin(token)){
         return Result.success("true");
      }
      return Result.fail("false");
     }
     //测试用
    
     @RequestMapping("/test")
     //@RequestHeader("token") String token,@RequestBody String request
     public Result test(@RequestHeader("token") String token,@RequestBody String info) {
      token = totoken(token);
      System.out.println("testinfo" + info);
      System.out.println("testtoken" +token);
     
      return Result.success("test:true");
     }
}
