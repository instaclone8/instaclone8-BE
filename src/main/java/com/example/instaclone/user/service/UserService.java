package com.example.instaclone.user.service;

import com.example.instaclone.jwt.JwtUtil;
import com.example.instaclone.user.dto.LoginRequestDto;
import com.example.instaclone.user.dto.SignupRequestDto;
import com.example.instaclone.user.entity.User;
import com.example.instaclone.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String email = signupRequestDto.getEmail();

        Optional<User> foundUsername = userRepository.findByUsername(username);

        if (foundUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다.");
        }

        Optional<User> foundEmail = userRepository.findByEmail(email);
        if (foundEmail.isPresent()) {
            throw new IllegalArgumentException("이메일이 중복됩니다");
        }

        User user = new User(username, password, email);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response){
            String email = loginRequestDto.getEmail();
            String password = loginRequestDto.getPassword();

            User user = userRepository.findByEmail(email).orElseThrow(
                    () -> new IllegalArgumentException("사용자를 찾을 수 없습니다"));

            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
            }
            response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(
                    user.getUsername()));
        }
}


