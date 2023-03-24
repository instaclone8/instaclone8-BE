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

    public MyPageResponseDto(Long userId, String username, String userImage, int postsCnt, List<PostResponseDto> posts) {
        this.userId = userId;
        this.username = username;
        this.userImage = userImage;
        this.postsCnt = postsCnt;
        this.posts = posts;
    }
}
