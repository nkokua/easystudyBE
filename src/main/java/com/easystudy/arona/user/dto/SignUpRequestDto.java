package com.easystudy.arona.user.dto;


import com.easystudy.arona.user.model.entity.Authority;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

//커스텀 어노테이션 적용(나중에)
@Getter
public class SignUpRequestDto {

    @NotBlank(message = "사용자 아이디는 필수 입력 항목입니다.")
    @Size(min = 4, max = 20, message = "사용자 아이디는 4자 이상 20자 이하로 입력해주세요.")
    private String nickname;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 6, max = 100, message = "비밀번호는 6자 이상 100자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    @Size(max = 50, message = "이메일은 50자 이하로 입력해주세요.")
    private String email;

    private Authority authority;

    public SignUpRequestDto() {
    }

    public SignUpRequestDto(String nickname, String password, String email, Authority authority) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.authority = authority;
    }

}