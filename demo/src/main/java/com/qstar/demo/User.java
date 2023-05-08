package com.qstar.demo;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@Component
@JsonPropertyOrder({"_id", "_name", "_email", "_passwd"})//规定转为json的格式
public class User {
    private int id;
    private String name;
    private String email;
    private String passwd;
    User(){
        
    }
    User(String Name,String Email,String Passwd)
    {
        this.name = Name;
        this.email = Email;
        this.passwd = Passwd;
    }
    public String get_name()
    {
        return name;
    }
    public String get_email()
    {
        return email;
    }
    public String get_passwd()
    {
        return passwd;
    }
    public int get_id()
    {
        return id;
    }
    public void set_name(String Name)
    {
        this.name = Name;
    }
    public void set_email(String Email)
    {
        this.email = Email;
    }
    public void set_passwd(String Passwd)
    {
        this.passwd = Passwd;
    }
    public void set_id(int Id)
    {
        this.id = Id;
    }
}
