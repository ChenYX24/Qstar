package com.qstar.demo.pojo;

public class Account {
    private String name;
    private String password;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public boolean verify(String name,String password){
        return true;
    }
}
