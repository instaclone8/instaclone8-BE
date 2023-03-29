package com.example.instaclone.domain.user.service;

import com.example.instaclone.domain.user.dto.*;
import com.example.instaclone.domain.user.entity.User;
import com.example.instaclone.domain.user.repository.UserRepository;

import com.example.instaclone.domain.post.dto.PostResponseDto;
import com.example.instaclone.domain.post.entity.Post;
import com.example.instaclone.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final com.example.instaclone.global.jwt.JwtUtil jwtUtil;

    private final PostRepository postRepository;


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
        response.addHeader(com.example.instaclone.global.jwt.JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(
                user.getUsername()));
    }

    // 유저이메일 중복 ck
    @Transactional(readOnly = true)
    public void checkemail(CheckEmailRequestDto checkEmailRequestDto) {
        String email = checkEmailRequestDto.getEmail();
        System.out.println("email  ="+ email);
        Optional<User> findemail = userRepository.findByEmail(email);
        if (findemail.isPresent()) {
            throw new IllegalArgumentException("이메일이 중복됩니다");
        }
    }


    // 마이페이지 조회 (토큰o)
    @Transactional(readOnly = true)
    public MyPageResponseDto getMyPage(User user, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdate").descending());
        Page<Post> entityPage = postRepository.findByUserOrderByCreatedateDesc(user, pageable);
        List<Post> entityList = entityPage.getContent();
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
            for (Post post : entityList) {
                postResponseDtos.add(new PostResponseDto(post));
        }
        return new MyPageResponseDto(user, postResponseDtos);
    }


    //유저아이디받기
    @Transactional(readOnly = true)
    public UserIdResponseDto getUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userId));
        Long foundUserId = user.getId();
        return new UserIdResponseDto(foundUserId);
    }
}

