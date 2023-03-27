package com.example.instaclone.user.dto;



import lombok.Getter;



import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequestDto {

    @NotBlank(message = "이메일을 입력해 주세요")
    private String email;
    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String password;
}
