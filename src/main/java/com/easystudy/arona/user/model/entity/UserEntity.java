package com.easystudy.arona.user.model.entity;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity  {

    @Id
    @Column(nullable = false, unique = true)
    private String uuid = UUID.randomUUID().toString();

    @Column
    private String nickname;

    @Column
    private String password;

    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    protected UserEntity() {
        super();
    }

    public String getUuid() {
        return uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Authority getRole() {
        return authority;
    }

    public static Builder builder() {
        return new Builder();
    }

    //2중 설정.
    private UserEntity(Builder builder) {
        this.nickname = builder.nickname;
        this.password = builder.password;
        this.email = builder.email;
        this.authority = builder.authority;
    }
    
    public static class Builder {
        private String nickname;
        private String password;
        private String email; // email 필드 추가 (선택적 필드에서 필수 필드로 이동 가능성 고려)
        private Authority authority = null;

        /**
         * 빌더의 기본 생성자입니다.
         * User.builder()를 통해 접근합니다.
         */
        private Builder() {
        }

        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder authority(Authority authority) {
            if (authority != null) {
                this.authority = authority;
            }else {
                this.authority = Authority.USER;
            }
            return this;
        }


        public UserEntity build() {
            // 필수 값 검증
            if (nickname == null || nickname.trim().isEmpty()) {
                throw new IllegalStateException("사용자 이름은 필수입니다.");
            }
            if (password == null || password.trim().isEmpty()) {
                throw new IllegalStateException("비밀번호는 필수입니다.");
            }
            if (email == null || email.trim().isEmpty()) { // email 필수 검증 유지
                throw new IllegalStateException("이메일은 필수입니다.");
            }
            if (authority == null) {
                authority = Authority.GUEST;
            }
            return new UserEntity(this);
        }
    }
}
