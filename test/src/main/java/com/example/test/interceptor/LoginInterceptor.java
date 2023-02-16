package com.example.test.interceptor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler){
        System.out.println("LoginInterceptor");
        //redisTemplate.opsForValue().set("admin","admin");
        Boolean res=redisTemplate.hasKey("home");
        System.out.println("redis  key是否存在"+res);    //判断key是否存在
        //falseResponse(response);
        return true;
    }

    private void falseResponse(HttpServletResponse response){
        PrintWriter writer=null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        try {
            writer= new PrintWriter(new OutputStreamWriter(response.getOutputStream()));
            writer.print("拦截器404");
        } catch (IOException e) {
            e.printStackTrace();}finally {
            if(writer != null){
                writer.close();
            }
        }
    }
}
