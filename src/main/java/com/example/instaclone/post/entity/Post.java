package com.example.instaclone.post.entity;

import com.example.instaclone.comment.entity.Comment;
import com.example.instaclone.like.entity.Likes;
import com.example.instaclone.post.dto.PostRequestDto;
import com.example.instaclone.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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

    private int commentCnt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    List<Likes> likes = new ArrayList<>();

    public Post (PostRequestDto reqDto, User user){

        this.content = reqDto.getContent();
        this.image = reqDto.getImage();
        this.user = user;
    }

    private int likeCnt = 0;

    private boolean likeCheck = false;


    public void updatePost (PostRequestDto reqDto){

        this.content = reqDto.getContent();
        this.image = reqDto.getImage();
    }


    public void commentCountPlus(){
        this.commentCnt += 1;
    }

    public void commentCountMinus(){
        this.commentCnt -= 1;
    }

    public void addLike(){

        this.likeCheck = true;
        this.likeCnt += 1;
    }


    public void withdrawLike() {
        this.likeCheck = false;
        this.likeCnt -= 1;
    }
}
