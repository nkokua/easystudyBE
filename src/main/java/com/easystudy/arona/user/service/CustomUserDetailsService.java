package com.easystudy.arona.user.service;

import com.easystudy.arona.user.dto.CustomUserPrincipal;
import com.easystudy.arona.user.model.entity.UserEntity;
import com.easystudy.arona.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository; //

    public CustomUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

            UserEntity user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + email));
        if(user != null) {
            return new CustomUserPrincipal(user);
        }
        return null;
    }



}

