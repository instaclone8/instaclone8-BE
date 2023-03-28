package com.example.instaclone.comment.entity;

import com.example.instaclone.post.entity.Timestamped;
import com.example.instaclone.comment.dto.CommentRequestDto;
import com.example.instaclone.post.entity.Post;
import com.example.instaclone.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor
@Getter
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comment(Post post, User user, CommentRequestDto commentRequestDto) {
        this.post = post;
        this.user = user;
        this.comment = commentRequestDto.getComment();

    }
}
