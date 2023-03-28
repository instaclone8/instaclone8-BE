package com.example.instaclone.comment.dto;

import com.example.instaclone.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String comment;
    private String username;
    private String userimage;
    private LocalDateTime createdate;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
//        유저네임
        this.username = comment.getUser().getUsername();
//        유저 이미지
        this.userimage = comment.getUser().getUserImage();
//        작성 날짜
        this.createdate = comment.getCreatedate().withNano(0);
    }
}