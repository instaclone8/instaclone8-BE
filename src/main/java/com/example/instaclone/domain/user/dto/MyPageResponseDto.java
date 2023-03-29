package com.example.instaclone.domain.user.dto;

import com.example.instaclone.domain.post.dto.PostResponseDto;
import com.example.instaclone.domain.post.entity.Post;
import com.example.instaclone.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @NoArgsConstructor @Setter
public class MyPageResponseDto {
    private Long id;
    private String username;
    private String userImage;
    private int postsCnt;
    private List<PostResponseDto> posts;

    public MyPageResponseDto(User user, List<PostResponseDto> posts, List<Post> postList) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.userImage = user.getUserImage();
        this.postsCnt = postList.size();
        this.posts = posts;
    }

}
