package com.example.instaclone.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class UsernameResponseDto {
    private String username;

    public UsernameResponseDto(String username) {
        this.username = username;
    }
}
