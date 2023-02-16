package com.example.test.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.test.util.JwtUtils;
import com.example.test.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(value = "用户登录，注册接口")
@RestController
@CrossOrigin(origins = "http://localhost:8080",allowedHeaders = "*",methods = {},allowCredentials = "true")
public class LogInOutRegController {

    @Autowired
    //private UserinfoMapper userinfoMapper;
    @ApiOperation("新用户注册账号")
    @PostMapping("/register")
    public String register(Userinfo userinfo) {
        System.out.println("打印userinfo   " + userinfo.toString());

        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.exists("select * from user where username='" + userinfo.getUsername() + "'");
        System.out.println("exists:  " + userinfoMapper.selectList(queryWrapper));
        userinfo.setSuperuser(false);     //默认创建用户为普通用户
//        userinfo.setSuperuser(true);     //创建用户为超级用户
        System.out.println("register_userinfo:"+userinfo);
        if (userinfoMapper.selectList(queryWrapper).isEmpty()) {     //判断注册用户是否存在，如果不存在即创建，
            System.out.println("该数据库没有用户");
            int i = userinfoMapper.insert(userinfo);
            if (i > 0) {
                return userinfo.getUsername()+"用户创建成功";
            } else {
                return userinfo.getUsername()+"用户创建失败";
            }
        } else {
            System.out.println("已存在该用户名");
            return "数据库已存在该用户名";
        }
    }

    @ApiOperation(value = "用户账户登录",notes = "用户账户登录接口")
    @PostMapping("/mylogin")
    public void login1(Userinfo userinfo, HttpServletResponse response) {
        //    System.out.println("登录用户信息"+userinfo.toString());
        //    //判断账号和密码是否存在且正确
        //    QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("username",userinfo.getUsername());
        //    List<Userinfo> res = userinfoMapper.selectList(queryWrapper);
        //System.out.println("登录用户信息"+res);
        //    if (res.isEmpty()) {
        //        System.out.println("数据库不存在该用户名");
        //        return Result.noExist();
        //    } else {
        //    System.out.println("exists:  " + res.get(0).getPassword());
        //    if(!Objects.equals(userinfo.getPassword(), res.get(0).getPassword())){
        //        return Result.error();
        //    }else{
        //        System.out.println("登陆成功");
        //        String token= JwtUtils.generateToken(userinfo.getUsername());
        //        boolean role_=res.get(0).isSuperuser();
        //        String role=role_?"SuperUser":"normaluser";
        //        System.out.println("用户角色  "+role);
        //        Cookie cookie=new Cookie("token",token);
        //        Cookie cookie1=new Cookie("User_role",role);
        //        response.addCookie(cookie);
        //        response.addCookie(cookie1);
        //        return Result.ok().data("token",token);
        //    }
        //}
        }
    @GetMapping("/info")
    public Result info(String token){
        String username=JwtUtils.getClaimByToken(token).getSubject();
        String url="666";
        return Result.ok().data("name",username).data("avatar",url);
    }
}


