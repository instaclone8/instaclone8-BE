package com.example.instaclone.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoResponseDto {
    private Long userId;
    private String username;

    public UserInfoResponseDto(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}