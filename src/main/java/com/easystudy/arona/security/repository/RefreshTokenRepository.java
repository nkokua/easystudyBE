package com.easystudy.arona.security.repository;

import com.easystudy.arona.security.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    // 토큰 값으로 RefreshToken 검색
    Optional<RefreshToken> findByToken(String tokenValue);

    // 사용자 이메일로 RefreshToken 검색
    Optional<RefreshToken> findByEmail(String email);

    // 사용자 이메일로 RefreshToken 삭제 (로그인/재발급 시 기존 토큰 정리용)
    void deleteByEmail(String email);

}