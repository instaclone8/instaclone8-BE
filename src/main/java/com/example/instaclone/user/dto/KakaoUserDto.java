package com.example.instaclone.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserDto {
    private Long id;
    private String email;
    private String nicknmae;

    public KakaoUserDto(Long id, String email, String nicknmae) {
        this.id = id;
        this.email = email;
        this.nicknmae = nicknmae;
    }
}
