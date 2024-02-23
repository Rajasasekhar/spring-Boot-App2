package com.hm.suthmodule.controller.utils;

import com.hm.authmodule.repository.UserRepository;
import com.hm.suthmodule.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class AuthUtils {

    @Autowired
    public AuthUtils(UserRepository userRepository){
        this.userRepo=userRepository;
    }

    private final UserRepository userRepo;
    private static final String SECRET_KEY = "";

    public static boolean isValidAuthToken(String authToken) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(authToken).getBody();
            Date expirationDate = claims.getExpiration();
            Date now = new Date();
            return expirationDate.after(now);
        } catch (ExpiredJwtException e) {
            return false;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean processData(User user) {
        try {
            userRepo.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
