package com.example.instaclone.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsernameResponseDto {
    private String username;
    private Long userId;

    public UsernameResponseDto(String username, Long foundUserId) {
        this.username = username;
        this.userId = foundUserId;
    }
}