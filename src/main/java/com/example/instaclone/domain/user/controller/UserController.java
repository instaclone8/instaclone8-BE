package com.example.instaclone.domain.user.controller;

import com.example.instaclone.domain.user.dto.*;
import com.example.instaclone.domain.user.service.KakaoService;
import com.example.instaclone.domain.user.service.UserService;
import com.example.instaclone.global.jwt.JwtUtil;
import com.example.instaclone.global.security.UserDetailsImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userservice;
    private final KakaoService kakaoService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDto> signup(@Validated @RequestBody SignupRequestDto signupRequestDto) {
        userservice.signup(signupRequestDto);
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "회원가입 성공"));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<MessageResponseDto> login(@Validated @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userservice.login(loginRequestDto, response);
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "로그인 성공"));
    }


    // 유저이메일 중복 ck
    @PostMapping("/checkemail")
    public ResponseEntity<MessageResponseDto> checkEmail(@Valid @RequestBody CheckEmailRequestDto checkEmailRequestDto) {
        userservice.checkemail(checkEmailRequestDto);
        return  ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "이메일 생성 가능!"));
    }



    // 마이페이지 조회 (토큰o)
    @GetMapping("/mypage")
    public MyPageResponseDto getMyPage(@RequestParam(value = "page", defaultValue = "0") int page, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userservice.getMyPage(userDetails.getUser(), page);
    }


    //카카오톡 로그인
    @GetMapping("/kakao/callback")
    public ResponseEntity<MessageResponseDto> kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드
        String createToken = kakaoService.kakaoLogin(code);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, createToken);
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "카카오 로그인 성공"));
    }

    // 아이디 받기
    @GetMapping("/userId")
    public UserIdResponseDto getUserId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userservice.getUserId(userDetails.getUser().getId());
    }
}
