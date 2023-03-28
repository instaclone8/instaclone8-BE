package com.example.instaclone.like;

import com.example.instaclone.like.Likes;
import com.example.instaclone.post.entity.Post;
import com.example.instaclone.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    Likes findByPostAndUser(Post post, User user);
}
