package it.zusby.ThinkQ.Auth2;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final Key key;
    private final long expirationMs;

    public JwtUtil(
            @Value("${spring.jwtKey}") String secret,
            @Value("${spring.jwtExpiration}") long expirationMs
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMs;
    }

    public String generateToken(String username, String role, boolean enabled) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("enabled",enabled)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token);
    }

    public String getUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }

    public String getRole(String token) {
        return parseToken(token).getBody().get("role", String.class);
    }


    public String generateToken(UserEntity user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .claim("enabled",user.isEnabled())
                .claim("birtday",user.getBirthday().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}