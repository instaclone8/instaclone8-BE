package com.example.instaclone.user.dto;

import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
public class SignupRequestDto {

    @NotBlank(message = "유저이름은 필수사항 입니다.")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "비밀번호는 필수사항 입니다.")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "이메일은 필수사항 입니다.")
    @Column(nullable = false)
    private String email;

    private boolean admin = false;

    private String adminToken = "";

}
