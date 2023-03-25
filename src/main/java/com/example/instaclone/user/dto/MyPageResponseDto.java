package com.example.instaclone.user.dto;

import com.example.instaclone.post.dto.PostResponseDto;
import com.example.instaclone.post.entity.Post;
import com.example.instaclone.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @NoArgsConstructor @Setter
public class MyPageResponseDto {
    private Long userId;
    private String username;
    private String userImage;
    private int postsCnt;
    private List<PostResponseDto> posts;

    public MyPageResponseDto(User user, List<PostResponseDto> posts) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.userImage = user.getUserImage();
        this.postsCnt = posts.size();
        this.posts = posts;
    }

}
