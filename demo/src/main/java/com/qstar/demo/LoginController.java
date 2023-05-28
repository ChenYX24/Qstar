package com.qstar.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import com.qstar.demo.Dao.Link;
import com.qstar.demo.pojo.User;
@RestController
@CrossOrigin
public class LoginController {
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
     public String login(@RequestBody String json){
         System.out.println(json);
         String result = " ";
         int id = -1;
         String name = "";
         String email = "";
         String password = "";
         email = userio.getKeyValueofJson(json, "email");
         password = userio.getKeyValueofJson(json, "password");
         if(userService.MatchEmailtoPasswd(email, password)){
             result = userService.GetUserByEmail(email);
             System.out.println(result);
             name = userio.getKeyValueofJson(json, "name");
             id = userio.getIDofObject("_email", email);
             link.SaveUser(result, new User(id, name, email, password));
         }
         return result;
     }
     //注册
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
     //发邮箱验证码
     @RequestMapping("/sendcode")
     public String sendcode(@RequestBody String json){
         System.out.println(json);
         String success = "false";
         String email = userio.getKeyValueofJson(json, "email");
         emailSender.sendcode(email);
         success = "true";
         return success;
     }
     //检验邮箱验证码是否正确
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
     //修改密码
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
     //自动登录
     @RequestMapping("/autologin")
     public String autologin(@RequestBody String request) {
         String json = userio.getKeyValueofJson(request, "token");
         int id = -1;
         String name = "";
         String result = " ";
         String email = "";
         String password = "";
         email = userio.getKeyValueofJson(json, "_email");
         password = userio.getKeyValueofJson(json, "_passwd");
         if(userService.MatchEmailtoPasswd(email, password)){
             result = userService.GetUserByEmail(email);
             name = userio.getKeyValueofJson(json, "_name");
             id = userio.getIDofObject("_email", email);
             link.SaveUser(result, new User(id, name, email, password));
             System.out.println(result);
         }
         return result;
     }
     //获取二维码base64
     @RequestMapping("/getqrcode")
     public String getqrcode(@RequestHeader("token") String token) {
        String qrcode = "";
        String id = userio.getKeyValueofJson(token,"id");
        qrcode = QRcodeGenerator.generate("https://www.baidu.com",id);
        return qrcode;
     }
     //测试用
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
     }
}
