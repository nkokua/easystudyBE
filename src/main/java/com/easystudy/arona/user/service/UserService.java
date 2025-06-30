package com.easystudy.arona.user.service;



import com.easystudy.arona.security.jwt.TokenProvider;
import com.easystudy.arona.security.model.entity.RefreshToken;
import com.easystudy.arona.security.repository.RefreshTokenRepository;
import com.easystudy.arona.user.dto.LoginRequestDto;
import com.easystudy.arona.user.dto.SignUpRequestDto;
import com.easystudy.arona.user.dto.TokenResponseDto;
import com.easystudy.arona.user.model.entity.UserEntity;
import com.easystudy.arona.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class UserService {
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authenticationManager;


    public UserService(TokenProvider tokenProvider, UserRepository userRepository, PasswordEncoder passwordEncoder, RefreshTokenRepository refreshTokenRepository, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenRepository = refreshTokenRepository;
        this.authenticationManager = authenticationManager;
    }


    @Transactional
    public TokenResponseDto login(LoginRequestDto loginRequestDto) {
        // 1. 사용자 인증 시
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );

        String email = authentication.getName();

        // 2. 새 AccessToken 생성
        String accessToken = tokenProvider.createAccessToken(authentication);

        // 3. 새 RefreshToken 생성
        String newRefreshTokenValue = tokenProvider.createRefreshToken();

        // 4. 기존 RefreshToken 있으면 조회
        Optional<RefreshToken> existing = refreshTokenRepository.findByEmail(email);

        Instant expiry = Instant.now().plusMillis(tokenProvider.getRefreshTokenValidityMilliseconds());

        if (existing.isPresent()) {
            // 기존 엔티티 업데이트 (중복 삽입 방지)
            RefreshToken tokenEntity = existing.get();
            tokenEntity.setToken(newRefreshTokenValue);
            tokenEntity.setExpiryDate(expiry);
            refreshTokenRepository.save(tokenEntity);
        } else {
            // 🔄 새 엔티티 저장
            RefreshToken tokenEntity = new RefreshToken(
                    email,             // 사용자 이름
                    newRefreshTokenValue, // 생성된 Refresh Token 값
                    expiry              // 만료 시간 설정
            );
            refreshTokenRepository.save(tokenEntity);
        }

        // 5. AccessToken 응답
        return new TokenResponseDto(accessToken, newRefreshTokenValue);
    }


    public void signUp(SignUpRequestDto requestDto) {
        if (userRepository.existsByNickname(requestDto.getNickname())) {
            throw new IllegalArgumentException("이미 사용 중인 사용자명입니다.");
        }

        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        UserEntity user = UserEntity.builder()
                .nickname(requestDto.getNickname())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .email(requestDto.getEmail())
                .authority(requestDto.getAuthority())
                .build();

        UserEntity saveUser = userRepository.save(user);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
