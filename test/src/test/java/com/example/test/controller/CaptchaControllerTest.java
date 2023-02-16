package com.example.test.controller;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

class CaptchaControllerTest {
    @Resource
    private RedisTemplate<String, String> redisTemplate;


}