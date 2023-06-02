package com.qstar.demo.pojo.Receiver;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorityReceive {//用于接受权限管理的人
    private String name;
    private String email;
    private String photo;
    private int authority;
}
