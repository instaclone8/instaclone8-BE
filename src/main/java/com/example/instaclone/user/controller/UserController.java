package com.example.instaclone.user.controller;

import com.example.instaclone.jwt.JwtUtil;
import com.example.instaclone.security.UserDetailsImpl;
import com.example.instaclone.user.dto.*;
import com.example.instaclone.user.service.KakaoService;
import com.example.instaclone.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
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
        return  ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "succss"));
    }

    //유저이름 중복 ck
    @PostMapping("/checkusername")
    public ResponseEntity<MessageResponseDto> checkUsername(@Valid @RequestBody CheckUsernameRequestDto checkUsernameRequestDto) {
        userservice.checkusername(checkUsernameRequestDto);
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "succss"));
    }

    // 마이페이지 조회 (토큰o)
    @GetMapping("/mypage/{userId}")
    public MyPageResponseDto getMyPage(@PathVariable Long userId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userservice.getMyPage(userId, userDetails.getUser());
    }
    //카카오톡 로그인
    @GetMapping("/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드
        String createToken = kakaoService.kakaoLogin(code, response);

        // Cookie 생성 및 직접 브라우저에 Set
        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, createToken.substring(7));
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/api/";
    }

}
