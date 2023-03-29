package com.example.instaclone.domain.post.entity;

import com.example.instaclone.domain.comment.entity.Comment;
import com.example.instaclone.domain.like.Likes;
import com.example.instaclone.domain.post.dto.PostRequestDto;
import com.example.instaclone.domain.user.entity.User;
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

    private String content;

    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int commentCnt;

    private String imageName;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Likes> likes = new ArrayList<>();

    private int likeCnt = 0;

    private boolean likeCheck = false;

    public Post (PostRequestDto reqDto, User user){
        this.content = reqDto.getContent();
        this.user = user;
    }

    public Post (String content, String image, String imageName, User user){
        this.content = content;
        this.image = image;
        this.imageName = imageName;
        this.user = user;
    }

    public void updatePost (String content){
        this.content = content;
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