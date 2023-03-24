package com.example.instaclone.user.dto;

public enum StatusEnum {

    LOGIN(200,"로그인 성공"),
    SIGN(200, "회원가입 성공");

    int statusCode;
    String msg;

    StatusEnum(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}

