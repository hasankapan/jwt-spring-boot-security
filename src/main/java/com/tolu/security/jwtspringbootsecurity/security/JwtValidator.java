package com.tolu.security.jwtspringbootsecurity.security;


import com.tolu.security.jwtspringbootsecurity.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private String secret = "tolusecret";

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();
            jwtUser.setUsername(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("password"));
            jwtUser.setRole((String) body.get("role"));
        } catch (Exception e){
            e.printStackTrace();
        }

        return jwtUser;
    }
}
