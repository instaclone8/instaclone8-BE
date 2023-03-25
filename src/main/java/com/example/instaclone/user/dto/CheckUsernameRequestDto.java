package com.example.instaclone.user.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CheckUsernameRequestDto {

    @NotBlank(message = "유저이름은 필수사항 입니다.")
    private String username;
}
