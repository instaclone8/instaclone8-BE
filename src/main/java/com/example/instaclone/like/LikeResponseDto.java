package com.example.instaclone.like;

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

}