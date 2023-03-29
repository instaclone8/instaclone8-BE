package com.example.instaclone.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserIdResponseDto {
    private Long userId;

    public UserIdResponseDto(Long userId) {
        this.userId = userId;
    }
}