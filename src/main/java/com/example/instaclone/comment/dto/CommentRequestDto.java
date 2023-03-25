package com.example.instaclone.comment.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class CommentRequestDto {
    @NotBlank(message = "댓글을 입력하세요.")
    @Size(min = 1, max = 70, message = "댓글은 최소 1자에서 최대 100자 이내여야 합니다.")
    private String comment;
}
