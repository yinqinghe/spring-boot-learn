package com.example.test.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class JwtAuthenticationTokenFilter extends BasicAuthenticationFilter {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    //@Autowired
    //private JwtUtils jwtUtils;
    //@Autowired
    //private UserDetailsService userDetailsService;
    @Value("${jwt.tokenHeader")
    private String tokenHeader;
    @Value("${jwgetClaimByTokent.tokenHead}")
    private String tokenHead;

    public JwtAuthenticationTokenFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //查看request所有请求头
        Enumeration<String> enumeration=request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name=enumeration.nextElement();
            System.out.println(name+"    "+request.getHeader(name));
        }
        //if(authHeader!=null&&authHeader.startsWith(this.tokenHead)){
        //    String authToken=authHeader.substring(this.tokenHead.length());
        //    Claims username= JwtUtils.getClaimByToken(authToken);
        //    LOGGER.info("checking username:{}",username);
            //if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
            //    UserDetails userDetails=this.userDetailsService.loadUserByUsername(String.valueOf(username));
            //    if(jwtUtils.validateToken(authToken,userDetails)){
            //        UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            //        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //        LOGGER.info("authentication user:{}",username);
            //        SecurityContextHolder.getContext().setAuthentication(authentication);
            //    }
            //}
        //
        String tokenHeader=request.getHeader("token");
        System.out.println("tokenHeader  :"+tokenHeader);
        if(Objects.equals(tokenHeader, "sss")){
            List<GrantedAuthority> authorities= new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_teacher1"));
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken("mike",30,authorities);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request,response);
    }
}
