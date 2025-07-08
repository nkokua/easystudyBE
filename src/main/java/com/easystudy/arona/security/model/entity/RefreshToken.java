package com.easystudy.arona.security.model.entity;

import jakarta.persistence.*;


import java.time.Instant;



@Entity
@Table
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email; // 사용자 식별자 (예: User 엔티티의 email 또는 ID)

    @Column(nullable = false, length = 1024) // 토큰 길이를 고려하여 충분한 길이 설정
    private String token;

    @Column(nullable = false)
    // UTC 기준으로 시을 정의함
    private Instant expiryDate;

    public RefreshToken() {
    }

    public RefreshToken(String email, String token, Instant expiryDate) {
        this.email = email;
        this.token = token;
        this.expiryDate = expiryDate;
    }



    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(this.expiryDate);
    }
}

/*
🚀 심화
- 대규모 서비스에서는 RefreshToken을 DB보다 Redis에 저장하여 속도 향상
  - Redis: TTL 기능으로 자동 만료 관리 가능
  - Key 구성 예: `refresh:userId` → value: token

// 해볼것!
- 보안 강화를 위한 확장 전략:
  - User-Agent, IP, deviceId 등 추가 메타데이터와 함께 저장
  - 같은 유저라도 다른 디바이스에선 다른 RefreshToken을 발급

- 토큰 블랙리스트 정책을 Redis Set으로 구현하여 재사용 방지 가능
* */