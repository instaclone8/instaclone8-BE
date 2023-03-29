package com.example.instaclone.domain.post.repository;

import com.example.instaclone.domain.post.entity.Post;
import com.example.instaclone.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreatedateDesc();

    List<Post> findByUserOrderByCreatedateDesc(User user);

    Page<Post> findByIdLessThanOrderByIdDesc(Long lastPostId, PageRequest pageRequest);

}
