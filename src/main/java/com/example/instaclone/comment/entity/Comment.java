//package com.example.instaclone.comment.entity;
//
//import com.example.instaclone.comment.dto.CommentRequestDto;
//import com.example.instaclone.post.entity.Post;
//import com.example.instaclone.user.entity.User;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//
//@Entity
//@NoArgsConstructor
//@Getter
//public class Comment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @ManyToOne
//    @JoinColumn(name = "POST_ID", nullable = false)
//    private Post post;
//
//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID", nullable = false)
//    private User user;
//
//    @NotBlank
//    private String comment;
//
//    public Comment(Post post, User user, CommentRequestDto commentRequestDto) {
//        this.post = post;
//        this.user = user;
//        this.comment = commentRequestDto.getComment();
//    }
//}
