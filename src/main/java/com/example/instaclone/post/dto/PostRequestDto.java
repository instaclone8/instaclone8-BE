package com.example.instaclone.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
public class PostRequestDto {

    private String content;

    private String image;

}
