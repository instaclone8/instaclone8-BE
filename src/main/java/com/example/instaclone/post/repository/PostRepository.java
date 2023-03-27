package com.example.instaclone.post.repository;

import com.example.instaclone.post.entity.Post;
import com.example.instaclone.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreatedateDesc();

    List<Post> findByUserOrderByCreatedateDesc(User user);


    Page<Post> findByIdLessThanOrderByIdDesc(Long lastPostId, PageRequest pageRequest);

}
