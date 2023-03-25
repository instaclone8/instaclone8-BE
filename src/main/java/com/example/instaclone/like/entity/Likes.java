package com.example.instaclone.like.entity;

import com.example.instaclone.post.entity.Post;
import com.example.instaclone.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private boolean likeCheck = false;

    public Likes(Post post, User user){
        this.post = post;
        this.user = user;
        this.likeCheck = true;
    }

    public Likes(){}

    public void changeLike(Likes like){
        if(like.likeCheck){
            this.likeCheck = false;
        } this.likeCheck = true;
     }

}
