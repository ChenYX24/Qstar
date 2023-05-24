package com.qstar.demo.pojo.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

public class JWTUtil {
    @Value("${jwt.secretKey}")
    private static String secretKey;
    public static String generate(String username){
        return Jwts.builder().claim("username",username).signWith(SignatureAlgorithm.HS256,secretKey).compact();
    }
}
