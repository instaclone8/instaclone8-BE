package com.example.instaclone.domain.comment.controller;

import com.example.instaclone.domain.comment.dto.CommentRequestDto;
import com.example.instaclone.domain.comment.service.CommentService;
import com.example.instaclone.global.security.UserDetailsImpl;
import com.example.instaclone.domain.user.dto.MessageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class CommentController {

    private final CommentService commentService;

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