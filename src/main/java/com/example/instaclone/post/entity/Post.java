package com.example.instaclone.post.entity;

import com.example.instaclone.comment.entity.Comment;
import com.example.instaclone.like.Likes;
import com.example.instaclone.post.dto.PostRequestDto;
import com.example.instaclone.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String content;

    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int commentCnt;

    @Column(nullable = true)
    private String imageName;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Likes> likes = new ArrayList<>();

    public Post (PostRequestDto reqDto, User user){

        this.content = reqDto.getContent();
//        this.image = reqDto.getImage();
        this.user = user;
    }
    public Post (String content, String image, String imageName, User user){
        this.content = content;
        this.image = image;
        this.imageName = imageName;
        this.user = user;
    }

    private int likeCnt = 0;

    private boolean likeCheck = false;

    public void updatePost (String content){
        this.content = content;
    }

//    public void updatePost (String image, String imageName, String content){
//        this.content = content;
//        this.image = image;
//        this.imageName = imageName;
//    }

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
