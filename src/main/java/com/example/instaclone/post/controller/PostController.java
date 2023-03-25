package com.example.instaclone.post.controller;

import com.example.instaclone.post.dto.PostRequestDto;
import com.example.instaclone.post.dto.PostResponseDto;
import com.example.instaclone.post.service.PostService;
import com.example.instaclone.security.UserDetailsImpl;
import com.example.instaclone.user.dto.MessageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<MessageResponseDto> createPost(@RequestBody PostRequestDto reqDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.createPost(reqDto, userDetails.getUser());
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "게시글 작성 성공!"));
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }
    //
    @GetMapping("/posts/{postId}")
    public PostResponseDto lookupPost(@PathVariable Long postId){
        return postService.lookupPost(postId);
    }

    @PatchMapping("/posts/{postId}")
    public ResponseEntity<MessageResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto reqDto){
        postService.updatePost(reqDto, postId);
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "게시글 수정 성공!"));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<MessageResponseDto> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "게시글 삭제 성공!"));
    }
}

