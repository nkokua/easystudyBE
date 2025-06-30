package com.easystudy.arona.user.dto;

public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;

    public TokenResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public String toString() {
        return "TokenResponseDTO{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}