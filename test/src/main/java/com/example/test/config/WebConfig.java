package com.example.test.config;

import com.example.test.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //拦截器在拦截的时候是在springcontext加载之前完成的，所以这个时候是没有bean注入到spring容器中的，@Autowried自然就获取不到
    @Bean                        //@Bean先把RedisTemplate加载了，然后再去拦截器里面注入
    public LoginInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/**");
        //registry.addInterceptor(getLoginInterceptor()).excludePathPatterns("/error");

    }
}
