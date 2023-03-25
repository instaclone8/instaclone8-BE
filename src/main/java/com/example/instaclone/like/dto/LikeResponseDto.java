package com.example.instaclone.like.dto;

import com.example.instaclone.like.entity.Likes;
import com.example.instaclone.post.entity.Post;
import com.example.instaclone.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LikeResponseDto {

    private User user;
    private Post post;
    private boolean likeCheck = false;

    public LikeResponseDto(Likes like) {
        this.user = like.getUser();
        this.post = like.getPost();
        this.likeCheck = like.isLikeCheck();
    }
}
