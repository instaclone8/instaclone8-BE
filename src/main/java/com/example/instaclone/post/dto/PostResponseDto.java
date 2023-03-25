package com.example.instaclone.post.dto;

import com.example.instaclone.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
public class PostResponseDto {

    private Long postId;
    private String username;
    private String userImage;
    private String image;
    private LocalDateTime createdate;
    private String content;
    private int likeCnt;
    private boolean likeCheck;
    private int commentCnt;

    public PostResponseDto(Post post){
        this.postId = post.getId();
        this.username = post.getUser().getUsername();
        this.userImage = post.getUser().getUserImage();
        this.image = post.getImage();
        this.createdate = post.getCreatedate().withNano(0);
        this.content = post.getContent();
        this.commentCnt = post.getCommentCnt();
        this.likeCnt = post.getLikeCnt();
        this.likeCheck = post.isLikeCheck();
    }
}
