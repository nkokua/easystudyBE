package com.easystudy.arona.user.controller;


import com.easystudy.arona.user.dto.LoginRequestDto;
import com.easystudy.arona.user.dto.SignUpRequestDto;
import com.easystudy.arona.user.dto.TokenResponseDto;
import com.easystudy.arona.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class  UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid LoginRequestDto dto, HttpServletRequest request) {
        TokenResponseDto response = userService.login(dto);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignUpRequestDto requestDto) {
       userService.signUp(requestDto);
    }
}
