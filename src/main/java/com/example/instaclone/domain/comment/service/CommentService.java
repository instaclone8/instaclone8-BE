package com.example.instaclone.domain.comment.service;

import com.example.instaclone.domain.comment.dto.CommentRequestDto;
import com.example.instaclone.domain.comment.entity.Comment;
import com.example.instaclone.domain.comment.repository.CommentRepository;
import com.example.instaclone.domain.post.entity.Post;
import com.example.instaclone.domain.post.repository.PostRepository;
import com.example.instaclone.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    @Transactional
    public void createComment(Long postId, CommentRequestDto commentRequestDto, User user) {
//        게시글 존재 여부 확인. 없으면 예외처리
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        post.commentCountPlus();
        Comment comment = new Comment(post, user, commentRequestDto);
        commentRepository.save(comment);
        post.getComments().add(comment);
    }

    //    2. 댓글 삭제 메서드
    @Transactional
    public void deleteComment(Long postId, Long commentId , User user) {
        //        게시글 존재 여부 확인. 없으면 예외처리
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
//        댓글 존재 여부 확인. 없으면 예외처리
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        post.commentCountMinus();
        commentRepository.deleteById(commentId);
    }
}
