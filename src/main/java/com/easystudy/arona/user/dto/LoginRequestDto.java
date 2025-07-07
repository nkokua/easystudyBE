package com.easystudy.arona.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 로그인 요청 시 사용될 DTO 입니다.
 */
public class LoginRequestDto {

    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    @Size(max = 50, message = "이메일은 50자 이하로 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 6, message = "비밀번호는 6자 이상으로 입력해주세요.")
    private String password;

    public LoginRequestDto() {
    }

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequestDto{" +
                "email='" + email + '\'' +
                ", password='[PROTECTED]'" + // 보안을 위해 비밀번호는 로그에 남기지 않음
                '}';
    }
}