package com.example.instaclone.user.entity;



import com.example.instaclone.comment.entity.Comment;
import com.example.instaclone.like.Likes;
import com.example.instaclone.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = true)
    private Long kakaoId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String userImage;

    @Column(nullable = false)
    private String email;

    @OneToMany
    private List<Post> posts = new ArrayList<>();

    @OneToMany
    private List<Likes> likes = new ArrayList<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String userImage, String email) {
        this.username = username;
        this.password = password;
        this.userImage = userImage;
        this.email = email;
    }

    public User(String username, Long kakaoId, String password, String email) {
        this.username = username;
        this.kakaoId = kakaoId;
        this.password = password;
        this.email = email;
    }

    public User kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }

}