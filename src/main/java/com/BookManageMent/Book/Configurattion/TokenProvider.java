package com.BookManageMent.Book.Configurattion;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenProvider {

    SecretKey secretKey =  Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public  String generateToken(Authentication authentication){

        String jwt = Jwts.builder().setIssuer("Book Management Team").setIssuedAt(
                        new Date()
                ).setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email",authentication.getName()).signWith(secretKey)
                .claim("role", authentication.getAuthorities())
                .compact();

        return "Bearer "+jwt;

    }

    public String getEmailFromToken(String jwt){
        jwt = jwt.substring(7);

        Claims claim = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();

        String email = String.valueOf(claim.get("email"));

        return  email;
    }



}
