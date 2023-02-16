package com.example.test.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {
    @GetMapping("/error/1")
    public String hello(HttpServletResponse response){

        return "hello world error";
    }
}
