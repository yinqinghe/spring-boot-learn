package com.example.test.util;

import java.util.HashMap;
import java.util.Map;

//统一返回结构的类
public class Result {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String,Object> data =new HashMap<>();
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    //把构造方法私有
    private Result() {}

    //成功的静态方法
    public static Result ok() {
        Result r=new Result();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage("success");
        return r;
    }

    //失败的静态方法
    public static Result error(){
        Result r=new Result();
        r.setSuccess(false);
        r.setCode(404);
        r.setMessage("密码错误");
        return r;
    }
    //禁止访问的静态方法
    public static Result forbid(){
        Result r=new Result();
        r.setSuccess(false);
        r.setCode(403);
        r.setMessage("无权限访问");
        return r;
    }
    public static Result noExist(){
        Result r=new Result();
        r.setSuccess(false);
        r.setCode(404);
        r.setMessage("数据库不存在该用户名");
        return r;
    }
    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    public Result data(Map<String,Object> map){
        this.setData(map);
        return this;
    }
}
