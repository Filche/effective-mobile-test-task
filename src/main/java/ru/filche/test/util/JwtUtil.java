package ru.filche.test.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private final String SECRET = "yourSuperSecretKeyForJWTWithAtLeast256Bits";
    private final long EXPIRATION = 86400000; // 24h

    public String generateToken(String username, Collection<? extends GrantedAuthority> roles) {
        String rolesStr = roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", rolesStr)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<SimpleGrantedAuthority> extractRoles(String token) {
        String roles = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token)
                .getBody().get("roles", String.class);
        if (roles == null) return Collections.emptyList();
        return Arrays.stream(roles.split(","))
                .map(r -> new SimpleGrantedAuthority(r.replace("ROLE_", "")))
                .collect(Collectors.toList());
    }
}
