package com.example.instaclone.domain.post.dto;

import com.example.instaclone.domain.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
@NoArgsConstructor
@Getter @Setter
public class PostResponseDto {

    private Long postId;
    private Long userId;
    private String username;
    private String userImage;
    private String image;
    private String content;
    private LocalDateTime createdate;
    private int commentCnt;
    private int likeCnt;
    private boolean likeCheck;
    private int postsCnt;

    public PostResponseDto(Post post){
        this.postId = post.getId();
        this.userId = post.getUser().getId();
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
