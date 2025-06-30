package com.easystudy.arona.security.jwt;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.Column;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;


@Component
public class TokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-token-validity-milliseconds}")
    private long ACCESS_TOKEN_EXPIRE_TIME;

    @Value("${jwt.refresh-token-validity-milliseconds}")
    private long REFRESH_TOKEN_EXPIRE_TIME;

    private SecretKey key;

    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }


    public String createAccessToken(Authentication authentication) {
        init();
        String nickname = authentication.getName();
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")); // 예: "ROLE_USER,ROLE_ADMIN"

        return Jwts.builder()
                .subject(nickname)
                .claim("roles", authorities)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key,Jwts.SIG.HS512) // ✅ signWith만 전달하면 HS256 자동 적용
                .compact();
    }

    public String createRefreshToken() {
        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, Jwts.SIG.HS512)
                .compact();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;

        } catch (ExpiredJwtException e) {
          //  throw new ExpiredJwtCustomException("Expired JWT token: " + e.getMessage());
        } catch (JwtException | IllegalArgumentException e) {
          //  throw new InvalidJwtCustomException("Invalid JWT token: " + e.getMessage(), e);
        }
        return false;
    }

    // 요청 헤더에서 Refresh Token 추출 (예: "X-Refresh-Token" 헤더 사용)
    public String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader("X-Refresh-Token");
    }

    public long getRefreshTokenValidityMilliseconds() {
        return REFRESH_TOKEN_EXPIRE_TIME;
    }
}
