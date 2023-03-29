package com.example.instaclone.domain.post.dto;

import com.example.instaclone.domain.comment.dto.CommentResponseDto;
import com.example.instaclone.domain.comment.entity.Comment;
import com.example.instaclone.domain.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class PostResponseDtoImpl extends PostResponseDto{

    private Long postId;

    private Long userId;
    private String username;
    private String userImage;
    private String image;
    private LocalDateTime createdate;
    private String content;
    private int likeCnt;
    private boolean likeCheck;
    private int commentCnt;
    private final List<CommentResponseDto> commentList = new ArrayList<>();

    public PostResponseDtoImpl(Post post){
        this.postId = post.getId();
        this.username = post.getUser().getUsername();
        this.userImage = post.getUser().getUserImage();
        this.userId = post.getUser().getId();
        this.image = post.getImage();
        this.createdate = post.getCreatedate().withNano(0);
        this.content = post.getContent();
        this.commentCnt = post.getCommentCnt();
        for (Comment comment : post.getComments()){
            commentList.add(new CommentResponseDto(comment));
        }
        this.likeCnt = post.getLikeCnt();
        this.likeCheck = post.isLikeCheck();
    }
}
