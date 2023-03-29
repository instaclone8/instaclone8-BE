package com.example.instaclone.domain.like;

import com.example.instaclone.domain.post.entity.Post;
import com.example.instaclone.domain.user.entity.User;
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
