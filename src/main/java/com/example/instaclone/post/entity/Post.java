package com.example.instaclone.post.entity;

import com.example.instaclone.comment.entity.Comment;
import com.example.instaclone.post.dto.PostRequestDto;
import com.example.instaclone.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int likeCnt;

    private int commentCnt;

    @OneToMany
    List<Comment> comments = new ArrayList<>();

//    @OneToMany
//    List<Likes> likes = new ArrayList<>();

    public Post (PostRequestDto reqDto){
        this.content = reqDto.getContent();
        this.image = reqDto.getImage();
    }

    public void updatePost (PostRequestDto reqDto){
        this.content = reqDto.getContent();
        this.image = reqDto.getImage();
    }

    public void commentCount(){
        this.commentCnt += 1;
    }
}
