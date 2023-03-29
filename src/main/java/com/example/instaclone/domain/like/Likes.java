package com.example.instaclone.domain.like;

import com.example.instaclone.domain.post.entity.Post;
import com.example.instaclone.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Likes {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Likes(Post post, User user){
        this.post = post;
        this.user = user;
    }
}
