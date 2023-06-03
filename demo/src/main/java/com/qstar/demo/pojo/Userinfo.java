package com.qstar.demo.pojo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@Data
@JsonPropertyOrder({"username", "email", "phoneNumber", "location","headPic"})//规定转为json的格式
public class Userinfo {
    String username;
    String email;
    String phoneNumber;
    String location;
    String headPic;
}
