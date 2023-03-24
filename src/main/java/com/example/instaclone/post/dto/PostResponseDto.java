package com.example.instaclone.post.dto;

import com.example.instaclone.comment.dto.CommentResponseDto;
import com.example.instaclone.comment.entity.Comment;
import com.example.instaclone.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class PostResponseDto {

    private Long postId;
    private String username;
//    private String userImage;
    private String content;
    private String image;
    private LocalDateTime createDate;
    private final List<CommentResponseDto> comments = new ArrayList<>();
    private int commentCnt;
    private int likeCnt;
    private boolean likeCheck;

    public PostResponseDto(Post post){
        this.postId = post.getId();
        this.username = post.getUser().getUsername();
        this.content = post.getContent();
        this.image = post.getImage();
        this.createDate = post.getCreateDate();
        this.commentCnt = post.getCommentCnt();
        for (Comment comment : post.getComments()){
            comments.add(new CommentResponseDto(comment));
        }
        this.likeCnt = post.getLikeCnt();
//        this.likeCheck = post.getUser().isLike();
    }
}
