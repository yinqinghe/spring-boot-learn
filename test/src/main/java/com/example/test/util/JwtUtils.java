package com.example.test.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class JwtUtils {
//    7天过期
    private static long expire=604800;
//    32位密钥
    private static String secret="fsdofdsndsfdsfosdfnds";

//    生成token
    public static String generateToken(String username){
        Date now=new Date();
        Date expiration=new Date(now.getTime()+1000*expire);
        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    //解析token
    public static Claims getClaimByToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
    public String getUserNameFromToken(String token){
        Claims claims=getClaimByToken(token);
        String username=claims.getSubject();
        return username;
    }
    public boolean validateToken(String token, UserDetails userDetails){
        String username=getUserNameFromToken(token);
        return username.equals(userDetails.getUsername());
    }

}
