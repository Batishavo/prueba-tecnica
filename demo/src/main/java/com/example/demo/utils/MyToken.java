package com.example.demo.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;


@Component
public class MyToken {
   
    
    public String createJason(){

        Date issuedAt = new Date(
            System.currentTimeMillis()
        );
        Date expiration = new Date(
            issuedAt.getTime()+(30*60*1000)
        );

        String jwt = Jwts.builder()
        .header()
        .type("JWT")
        .and()
        .subject("jwt")
        .expiration(expiration)
        .issuedAt(issuedAt)
        .compact();

        return jwt;
    }



    public void verifyJws(String jwt){
        Jwts.parser().build();
    }

    

}
