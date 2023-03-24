package com.example.instaclone.comment.controller;

import com.example.instaclone.comment.dto.CommentRequestDto;
import com.example.instaclone.comment.service.CommentService;
import com.example.instaclone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class CommentController {

    private final CommentService commentService;

    //    1. 댓글 작성 API
    @PostMapping("/{postId}/comments")
    public ResponseEntity<MessageResponseDto> createComment(@PathVariable Long postId,
                                                            @RequestBody @Valid CommentRequestDto commentRequestDto,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.createComment(postId, commentRequestDto, userDetails.getUser());
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "댓글 작성 성공."));
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<MessageResponseDto> deleteComment(@PathVariable Long postId, @PathVariable Long commentId,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(postId, commentId, userDetails.getUser());
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "댓글 삭제 성공."));
    }
}
