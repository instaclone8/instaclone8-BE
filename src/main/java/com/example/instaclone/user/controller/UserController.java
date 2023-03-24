package com.example.instaclone.user.controller;

import com.example.instaclone.user.dto.*;
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

@RestController
@RequiredArgsConstructor
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

    // 마이페이지
//    @GetMapping("/mypage{userId}")

    // 유저이메일 중복 ck
    @PostMapping("/checkemail")
    public ResponseEntity<MessageResponseDto> checkemail(@Valid @RequestBody CheckEmailRequestDto checkEmailRequestDto) {
        userservice.checkemail(checkEmailRequestDto);
        return  ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "succss"));
    }

    //유저이름 중복 ck
    @PostMapping("/checkusername")
    public ResponseEntity<MessageResponseDto> checkusername(@Valid @RequestBody CheckUsernameRequestDto checkUsernameRequestDto) {
        userservice.checkusername(checkUsernameRequestDto);
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "succss"));
    }
}
