package com.qstar.demo;

import org.springframework.beans.factory.annotation.Autowired;
import com.qstar.demo.pojo.User;
import com.qstar.demo.pojo.Userinfo;
import com.qstar.demo.pojo.writeAndRead.ObjReader;
import com.qstar.demo.pojo.writeAndRead.ObjWriter;
import com.qstar.demo.Dao.Link;
import java.util.Map;
import com.qstar.demo.EmailSender;
import org.springframework.beans.factory.annotation.Value;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.JdbcTemplate;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.core.JsonProcessingException;
public class UserService {
	// @Autowired
    // private JdbcTemplate jdbcTemplate;
    // UserService(JdbcTemplate jdbcTemplate){
    //     this.jdbcTemplate = jdbcTemplate;
    // }
	@Value("${store.base}")
    private String base;
    @Value("${store.questionaireRoad}")
    private String questionaireRoad;
    @Value("${store.userRoad}")
    private String userRoad;
	@Autowired
	DataIO userio;
	@Autowired
	Link link;
	@Autowired
	EmailSender emailSender;
	@Autowired
	ObjWriter objWriter;
	@Autowired
	ObjReader objReader;
	//登录
	public String login(String info){
		String result = " ";
        String email = "";
        String password = "";
        email = userio.getKeyValueofJson(info, "email");
        password = userio.getKeyValueofJson(info, "password");
		// this.MatchEmailtoPasswd(email, password)
        if(this.MatchEmailtoPasswd(email, password)){
			try {
				System.out.println(email);
				User user = objReader.readUser(email);
				System.out.println("useremail" + user.getEmail());
				result = userio.getIEByEmail(email);
				System.out.println(result);
				link.put(result,user);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
        }
		return result;
	}
	//注册
	public String register(String info){
		String result = "";
		String username = " ";
        String email = " ";
        String password = " ";
        email = userio.getKeyValueofJson(info, "email");
        if(this.hasRegister(email)){
           return "该邮箱已经被注册过";
        }
        username = userio.getKeyValueofJson(info, "username");
        password = userio.getKeyValueofJson(info, "password");
        User user = new User(username, email, password);
		this.SaveUser(user);
        result = userio.getIEByEmail(email);
		return result;
	}
	//自动登录
	public String autoLogin(String info){
		String result = "fail";
		String token = userio.getKeyValueofJson(info, "token");
		System.out.println("自动登录用的token:"+token);
		Map<String,User> map = link.getMap();
		if(map.get(token) != null){
			result = "success";
			System.out.println("自动登录成功！");
		}
		return result;
	}
	//发送邮箱验证码
	public String sendcode(String info){
		String result = "false";
		String email = userio.getKeyValueofJson(info, "email");
		emailSender.sendcode(email);
		result = "true";
		return result;
	}
	//检查验证码是否正确
	public String checkcode(String info){
		String success = "false";
		String email = userio.getKeyValueofJson(info, "email");
		System.out.println(email);
        String code = userio.getKeyValueofJson(info, "code");
		System.out.println(code);
        success = emailSender.checkcode(email, code);
        System.out.println(success);
		return success;
	}
	public void SaveUser(User user){
		// String sql = "INSERT INTO users(NAME,EMAIL,PASSWD) VALUES (?,?,?)";
		// this.jdbcTemplate.update(sql,user.getname(), user.getemail(),user.getpasswd());
		user.setId(userio.getMaxId() + 1);
		System.out.println("userid= " + user.getId());
		userio.writeID_Email(user.getId(), user.getEmail());
		try {
			objWriter.writeUser(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	//获取用户信息，返回JSON
	public String GetUserByEmail(String Email){
		String result = " ";
		// String sql = "SELECT * FROM users WHERE EMAIL = ?";
		// List<User> users = this.jdbcTemplate.query(sql, new UserMapper(), new Object[]{Email});
		// User user = users.isEmpty() ? null : users.get(0);
		// ObjectMapper objectMapper = new ObjectMapper();
		// result = objectMapper.writeValueAsString(user);
		try {
			User user = objReader.readUser(Email);
			userio.getJsonById(user.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	//修改用户密码
	public String ChangePasswd(String token,String info){
		// String sql = "update users set PASSWD = ? where EMAIL = ?";
		// this.jdbcTemplate.update(sql,passwd,email);
		String passwd = userio.getKeyValueofJson(info, "password");
		Map<String,User> map = link.getMap();
		if(map.get(token) != null){
			User user = map.get(token);
			user.setPasswd(passwd);
			try {
				objWriter.writeUser(user);
				String success = "false";
				if(user.getPasswd().hashCode() == passwd.hashCode()){
					success = "true";
					return success;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				
			}
		}
		return "false";
        // if(userio.getKeyValueofJson(userjson, "_passwd").hashCode() == passwd.hashCode()){
        //     success = "true";
        // }
		// int id = userio.getIDofObject(Email);
		//userio.changePasswdById(id,passwd);
	}
	//找回用户密码
	public String FindPasswd(String info){
		// String sql = "update users set PASSWD = ? where EMAIL = ?";
		// this.jdbcTemplate.update(sql,passwd,email);
		String passwd = userio.getKeyValueofJson(info, "password");
		String email = userio.getKeyValueofJson(info, "email");
		try {
			User user = objReader.readUser(email);
			user.setPasswd(passwd);
			objWriter.writeUser(user);
			return "true";
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "false";
	}
	//修改位置和电话号码
	public void ChangPLByID(String token,String info){
		String phoneNumber = userio.getKeyValueofJson(info, "phoneNumber");
        String location = userio.getKeyValueofJson(info, "location");
		Map<String,User> map = link.getMap();
		if(map.get(token) != null){
			User user = map.get(token);
			user.setPhonenumber(phoneNumber);
			user.setLocation(location);
			try {
				objWriter.writeUser(user);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	//获取个人详情页信息
	public Userinfo getUserinfo(String info){
		Userinfo userinfo = new Userinfo();
		String email = userio.getKeyValueofJson(info, "email");
		try {
			User user = objReader.readUser(email);
			userinfo.setEmail(email);
			userinfo.setUsername(user.getName());
			userinfo.setPhoneNumber(user.getPhonenumber());
			userinfo.setLocation(user.getLocation());
			userinfo.setHeadPic(user.getHeadPic());
			return userinfo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	//判断邮箱和密码是否匹配
	public Boolean MatchEmailtoPasswd(String Email,String Passwd){
		try {
			User user = objReader.readUser(Email);
			if(user == null)
			{
				return false;
			}
			if(user.getPasswd().hashCode() == Passwd.hashCode())
			{
				System.out.println("账号密码匹配正确");
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	//判断是否有相同的邮箱
	public boolean hasRegister(String email){
		System.out.println("判断路径为:"+base+"/"+userRoad+"/"+email+".txt");
		boolean temp = userio.hasFile(base+"/"+userRoad+"/"+email+".txt");
		if(temp){
			System.out.println("出现相同的邮箱！");
			return true;
		}
		return false;
	}
}
