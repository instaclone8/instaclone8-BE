package com.example.instaclone.domain.user.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
public class CheckEmailRequestDto {

    @NotEmpty(message = "이메일은 필수사항 입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "이메일 형식에 맞지 않습니다.")
    private String email;
}
