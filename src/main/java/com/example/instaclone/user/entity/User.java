package com.example.instaclone.user.entity;

import com.example.instaclone.jwt.UserRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter @Entity(name = "users") @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(nullable = false)
    private String email;

    public User(String username, String password, UserRoleEnum role, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;

    }

}
