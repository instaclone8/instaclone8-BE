package com.example.instaclone.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
public class SignupRequestDto {

    @NotBlank(message = "유저이름은 필수사항 입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수사항 입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{6,15}$", message = "password는 6~15글자, 알파벳, 숫자, 특수문자를 최소 하나씩 입력해야 합니다.")
    private String password;

    @NotEmpty(message = "이메일은 필수사항 입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "이메일 형식에 맞지 않습니다.")
    private String email;


}
