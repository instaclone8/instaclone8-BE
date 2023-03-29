package com.example.instaclone.domain.user.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MessageResponseDto {

    private int statusCode;
    private String message;

    public MessageResponseDto(HttpStatus httpStatus, String message) {
        this.statusCode = httpStatus.value();
        this.message = message;
    }
}