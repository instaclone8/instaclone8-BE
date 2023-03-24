package com.example.instaclone.user.controller;

import com.example.instaclone.user.dto.MessageResponseDto;
import com.example.instaclone.user.dto.LoginRequestDto;
import com.example.instaclone.user.dto.SignupRequestDto;
import com.example.instaclone.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userservice;

    //회원가입
    @PostMapping("/signup")
    public MessageResponseDto signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return userservice.signup(signupRequestDto);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody @Valid LoginRequestDto loginRequestDto,
            HttpServletResponse response) {
        return ResponseEntity.ok().body(userservice.login(loginRequestDto, response));
    }




}
