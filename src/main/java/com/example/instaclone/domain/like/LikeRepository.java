package com.example.instaclone.domain.like;

import com.example.instaclone.domain.post.entity.Post;
import com.example.instaclone.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    Likes findByPostAndUser(Post post, User user);
}
