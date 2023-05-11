package com.qstar.demo;

import org.springframework.beans.factory.annotation.Autowired;

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
	private String path = "user.json";
	@Autowired
	DataIO userio;
	UserService(String Path){
		this.path = Path;
	}
	//注册用户
	public void SaveUser(User user){
		// String sql = "INSERT INTO users(NAME,EMAIL,PASSWD) VALUES (?,?,?)";
		// this.jdbcTemplate.update(sql,user.get_name(), user.get_email(),user.get_passwd());
		userio = new DataIO(path);
		user.set_id(userio.getMaxId() + 1);
		System.out.println("userid= " + user.get_id());
		userio.writeObject(user);
	}
	//获取用户信息，返回JSON
	public String GetUserByEmail(String Email){
		String result = " ";
		// String sql = "SELECT * FROM users WHERE EMAIL = ?";
		// List<User> users = this.jdbcTemplate.query(sql, new UserMapper(), new Object[]{Email});
		// User user = users.isEmpty() ? null : users.get(0);
		// ObjectMapper objectMapper = new ObjectMapper();
		// result = objectMapper.writeValueAsString(user);
		userio = new DataIO(path);
		int id = userio.getIDofObject("_email", Email);
		result = userio.getJsonById(id);
		return result;
	}
	public String GetUserById(int id){
		String result = " ";
		// String sql = "SELECT * FROM users WHERE EMAIL = ?";
		// List<User> users = this.jdbcTemplate.query(sql, new UserMapper(), new Object[]{Email});
		// User user = users.isEmpty() ? null : users.get(0);
		userio = new DataIO(path);
		result = userio.getJsonById(id);
		return result;
	}
	//修改用户密码
	public void ChangePasswdByEmail(String Email,String passwd){
		// String sql = "update users set PASSWD = ? where EMAIL = ?";
		// this.jdbcTemplate.update(sql,passwd,email);
		userio = new DataIO(path);
		int id = userio.getIDofObject("_email", Email);
		userio.changePasswdById(id,passwd);
	}
	//判断邮箱和密码是否匹配
	public Boolean MatchEmailtoPasswd(String Email,String Passwd){
		String json = "";
		json = GetUserByEmail(Email);
		User user = userio.switchJsonToObject(json,User.class);
		if(user.get_passwd().hashCode() == Passwd.hashCode())
		{
			return true;
		}
		return false;
	}
}
