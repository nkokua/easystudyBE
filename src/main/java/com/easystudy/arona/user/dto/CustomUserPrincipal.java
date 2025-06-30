package com.easystudy.arona.user.dto;

import com.easystudy.arona.user.model.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserPrincipal implements UserDetails {
    private UserEntity user;

    public CustomUserPrincipal(UserEntity user) {
        this.user = user;
    }

    @Override
    // 이 사용자의 사용자 이메일반환 (구현때문에 어쩔수가없다.)
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    // 이 사용자의 비밀번호를 반환
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    // 이 사용자에게 부여된 권한(예: 역할) 목록을 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole().toString();
            }
        });

        return collection;
    }

    @Override
    // 이 사용자가 활성화되어 있는지 여부
    public boolean isEnabled() {
        return true;
    }

    @Override
    // 이 사용자의 자격 증명(비밀번호)이 만료되지 않았는지 여부
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    // 이 사용자 계정이 잠겨 있지 않은지 여부
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    // 이 사용자 계정이 만료되지 않았는지 여부
    public boolean isAccountNonExpired() {
        return true;
    }
}