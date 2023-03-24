package com.example.instaclone.user.controller;

import com.example.instaclone.user.dto.MessageResponseDto;
import com.example.instaclone.user.dto.LoginRequestDto;
import com.example.instaclone.user.dto.SignupRequestDto;
import com.example.instaclone.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<MessageResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        userservice.signup(signupRequestDto);
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "회원가입 성공"));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<MessageResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userservice.login(loginRequestDto, response);
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "로그인 성공"));
    }

//    마이페이지
//    @GetMapping("/mypage{userId}")





}
