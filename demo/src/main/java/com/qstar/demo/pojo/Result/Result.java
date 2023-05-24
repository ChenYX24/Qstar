package com.qstar.demo.pojo.Result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;
    public static Result success(){
        return new Result(1,"success",null);
    }
    public static Result success(String message){
        return new Result(1,message,null);
    }
    public static Result fail(String message){
        return new Result(0,message,null);
    }
    public static Result success(Object o){
        return new Result(1,"success",o);
    }
}
