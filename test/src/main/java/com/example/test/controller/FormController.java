package com.example.test.controller;

import com.example.test.util.JwtUtils;
import com.example.test.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class FormController {

    @PostMapping("/form")
    public Result fomr(String username, String password, HttpServletResponse response) {

        System.out.println(username + "   " + password);
        String token= JwtUtils.generateToken("mike");
        System.out.println(token);
        String username1= JwtUtils.getClaimByToken(token).getSubject();
        System.out.println("用户订单添加："+username1);
        Cookie cookie = new Cookie("token", "cookie");
        response.addCookie(cookie);

        return Result.ok().data("token",token);
        //return "Ok";
    }
}
