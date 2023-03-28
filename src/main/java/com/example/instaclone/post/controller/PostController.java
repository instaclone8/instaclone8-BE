package com.example.instaclone.post.controller;

import com.example.instaclone.like.LikeService;
import com.example.instaclone.post.dto.PostResponseDto;
import com.example.instaclone.post.dto.PostResponseDtoImpl;
import com.example.instaclone.post.service.PostService;
import com.example.instaclone.security.UserDetailsImpl;
import com.example.instaclone.user.dto.MessageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    private final LikeService likeService;

    @PostMapping("/posts")
    public ResponseEntity<MessageResponseDto> createPost(@RequestPart(value = "image", required = true) MultipartFile image, String content, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        postService.createPost(image, content, userDetails.getUser());
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "게시글 작성 성공!"));
    }

    @GetMapping("/posts/page")
    public List<PostResponseDto> fetchPages(@RequestParam Long lastPostId){
        return postService.fetchPages(lastPostId, 10);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/posts/{postId}")
    public PostResponseDtoImpl lookupPost(@PathVariable Long postId){
        return postService.lookupPost(postId);
    }

    @PatchMapping("/posts/{postId}")
    public ResponseEntity<MessageResponseDto> updatePost(@PathVariable Long postId, @ModelAttribute MultipartFile image, String content, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.updatePost(postId, image, content, userDetails.getUser());
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "게시글 수정 성공!"));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<MessageResponseDto> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.deletePost(postId, userDetails.getUser());
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "게시글 삭제 성공!"));
    }

    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<MessageResponseDto> setLike(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        likeService.setLike(postId, userDetails.getUser());
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "좋아요 성공!"));
    }
}

