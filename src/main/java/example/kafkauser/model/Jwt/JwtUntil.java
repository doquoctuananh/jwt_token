package example.kafkauser.model.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUntil {
    @Value("${jwt.secret}")
    private String key;

    @Value("${jwt.expiration}")
    private String expiration;

    // create key
    public Key generateKeys() {
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    // create token
    public String generateToken(String username,String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
                .signWith(SignatureAlgorithm.HS512, generateKeys())
                .compact();
    }

    //extra token
    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(generateKeys())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // get username to token
    public String getUsername(String token) {
        String username = null;
        Claims claims = parseToken(token);
        username= claims.getSubject();
        return username;
    }

    //get role to token
    public String getRole(String token) {
        String role = null;
        Claims claims = parseToken(token);
        role = claims.get("role", String.class);
        return role;
    }

    // kiem tra xem het han chua
    public boolean isExpired(String token) {
        Claims claims = parseToken(token);
        boolean expired = claims.getExpiration().before(new Date());
        return expired;
    }

    // kiem tra token co phai cua username khong
    public boolean isTokenExpired(String token,String username) {
        boolean verify = !isExpired(token) && username.equals(getUsername(token));
        return verify;
    }

}
