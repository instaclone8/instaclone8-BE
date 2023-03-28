package com.example.instaclone.post.dto;

import com.example.instaclone.comment.dto.CommentResponseDto;
import com.example.instaclone.comment.entity.Comment;
import com.example.instaclone.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class PostResponseDto {

    private Long postId;
    private String username;
    private String userImage;
    private String image;
    private String content;
    private LocalDateTime createdate;
    private int commentCnt;
    private int likeCnt;
    private boolean likeCheck;

    public PostResponseDto(Post post){
        this.postId = post.getId();
        this.username = post.getUser().getUsername();
        this.userImage = post.getUser().getUserImage();
        this.image = post.getImage();
        this.content = post.getContent();
        this.createdate = post.getCreatedate().withNano(0);
        this.commentCnt = post.getCommentCnt();
        this.likeCnt = post.getLikeCnt();
        this.likeCheck = post.isLikeCheck();
    }
}
