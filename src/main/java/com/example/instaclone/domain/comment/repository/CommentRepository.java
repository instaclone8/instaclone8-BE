package com.example.instaclone.domain.comment.repository;

import com.example.instaclone.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
