package com.easystudy.arona.user.repository;

import com.easystudy.arona.user.model.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByNickname(@NotBlank(message = "사용자 아이디는 필수 입력 항목입니다.") @Size(min = 4, max = 20, message = "사용자 아이디는 4자 이상 20자 이하로 입력해주세요.") String nickname);

    boolean existsByEmail(@NotBlank(message = "이메일은 필수 입력 항목입니다.") @Email(message = "유효한 이메일 형식이 아닙니다.") @Size(max = 50, message = "이메일은 50자 이하로 입력해주세요.") String email);


    Optional<UserEntity> findByEmail(String email);
}
