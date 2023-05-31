package com.qstar.demo;

import org.springframework.beans.factory.annotation.Autowired;
import com.qstar.demo.pojo.User;
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
	private String base = "./userinfo";
	@Autowired
	DataIO userio;
	//注册用户
	public void SaveUser(User user){
		// String sql = "INSERT INTO users(NAME,EMAIL,PASSWD) VALUES (?,?,?)";
		// this.jdbcTemplate.update(sql,user.get_name(), user.get_email(),user.get_passwd());
		user.set_id(userio.getMaxId() + 1);
		System.out.println("userid= " + user.get_id());
		userio.writeID_Email(user.get_id(), user.get_email());
		userio.writeObject(user,user.get_email());
	}
	//获取用户信息，返回JSON
	public String GetUserByEmail(String Email){
		String result = " ";
		// String sql = "SELECT * FROM users WHERE EMAIL = ?";
		// List<User> users = this.jdbcTemplate.query(sql, new UserMapper(), new Object[]{Email});
		// User user = users.isEmpty() ? null : users.get(0);
		// ObjectMapper objectMapper = new ObjectMapper();
		// result = objectMapper.writeValueAsString(user);
		int id = userio.getIDofObject(Email);
		result = userio.getJsonById(id);
		return result;
	}
	public String GetUserById(int id){
		String result = " ";
		// String sql = "SELECT * FROM users WHERE EMAIL = ?";
		// List<User> users = this.jdbcTemplate.query(sql, new UserMapper(), new Object[]{Email});
		// User user = users.isEmpty() ? null : users.get(0);
		result = userio.getJsonById(id);
		return result;
	}
	//修改用户密码
	public void ChangePasswdByEmail(String Email,String passwd){
		// String sql = "update users set PASSWD = ? where EMAIL = ?";
		// this.jdbcTemplate.update(sql,passwd,email);
		int id = userio.getIDofObject(Email);
		userio.changePasswdById(id,passwd);
	}
	//修改位置和电话号码
	public void ChangPLByID(int Id,String PhoneNumber,String Location){
		userio.changeLocationById(Id, Location);
		userio.changePhoneById(Id, PhoneNumber);
	}
	//判断邮箱和密码是否匹配
	public Boolean MatchEmailtoPasswd(String Email,String Passwd){
		String json = "";
		json = GetUserByEmail(Email);
		User user = userio.switchJsonToObject(json,User.class);
		if(user == null)
		{
			return false;
		}
		if(user.get_passwd().hashCode() == Passwd.hashCode())
		{
			System.out.println("账号密码匹配正确");
			return true;
		}
		return false;
	}
	//判断是否有相同的邮箱
	public boolean hasRegister(String email){
		boolean temp = userio.hasFile(base + "/" + email + ".json");
		if(temp){
			return true;
		}
		return false;
	}
	//获取IE
	public String getIEByid(int id){
		String result = "";
		return result;
	}
}
