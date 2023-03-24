package com.example.instaclone.user.service;

import com.example.instaclone.exception.ApiException;
import com.example.instaclone.jwt.JwtUtil;
import com.example.instaclone.jwt.UserRoleEnum;
import com.example.instaclone.user.dto.*;
import com.example.instaclone.user.entity.User;
import com.example.instaclone.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private final String ADMIN_TOKEN = "asdfasdfasdf";

    @Transactional
    public MessageResponseDto signup(SignupRequestDto signupRequestDto) {

        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String email = signupRequestDto.getEmail();

        Optional<User> userfind = userRepository.findByUsername(username);
        if (userfind.isPresent()) {
            throw new IllegalArgumentException("사용자가 중복됩니다");
        }

        Optional<User> emailfind = userRepository.findByEmail(signupRequestDto.getEmail());
        if (emailfind.isPresent()) {
            throw new IllegalArgumentException("이메일이 중복됩니다");
        }
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 일치하지 않습니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        userRepository.save(new User(username, password, role, email));
        return new MessageResponseDto(StatusEnum.SIGN);
    }

    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response){
            String email = loginRequestDto.getEmail();
            String password = loginRequestDto.getPassword();

            User user = userRepository.findByEmail(email).orElseThrow(
                    () -> new IllegalArgumentException("사용자를 찾을 수 없습니다"));


            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
            }
            response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(
                    user.getUsername(),
                    user.getRole()));
            return new LoginResponseDto(StatusEnum.LOGIN);
        }
}


