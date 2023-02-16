package com.example.test.config;

import com.example.test.component.JwtAuthenticationTokenFilter;
import com.example.test.component.RestAuthenticationEntryPoint;
import com.example.test.component.RestfulAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    //链式编程
    @Override
    protected void configure(HttpSecurity http) throws  Exception{
//        首页所有人可以访问，功能也只有对应权限的人才能访问
        //基于Token不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //请求授权的规则
        http.authorizeHttpRequests()
                .antMatchers("/").permitAll()
                //.antMatchers("/form").hasRole("superuser1")
                .antMatchers("/error/**").hasRole("teacher")
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .and().csrf().disable();       //关闭csrf
//        前端浏览器请求login接口，解决请求返回302重定向
//        http.formLogin().loginProcessingUrl("login");

        //http.antMatcher("/login").anonymous();
        http.authorizeHttpRequests()
                .and().cors();   //使用已有的跨域规则
        //没有权限默认会到登录页面，需要开启登录的页面
        //login
        http.formLogin();
        //添加JWT filter
        http.addFilter(new JwtAuthenticationTokenFilter(authenticationManager()));

        //禁用缓存
        http.headers().cacheControl();
        //添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }
    //@Autowired
    //UserinfoService userinfoService = new UserinfoService();
    //指定密码的加密方式
    @SuppressWarnings("deprecation")
    @Bean
    PasswordEncoder passwordEncoder(){
        //不对密码进行加密
        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
    }

    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //auth.userDetailsService(userinfoService);     //spring security  登录的用户验证数据库
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("mikejordan").password(new BCryptPasswordEncoder().encode("123456")).roles("superuser15");

    }
}
