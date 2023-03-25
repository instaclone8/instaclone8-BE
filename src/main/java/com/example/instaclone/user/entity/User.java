package com.example.instaclone.user.entity;



import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private Long kakaoId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String userImage;

    @Column(nullable = false)
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

//    public User(String username, String password, String userImage, String email) {
//        this.username = username;
//        this.password = password;
//        this.userImage = userImage;
//        this.email = email;
//    }

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
