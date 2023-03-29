package com.example.instaclone.domain.like;

import com.example.instaclone.domain.post.entity.Post;
import com.example.instaclone.domain.post.repository.PostRepository;
import com.example.instaclone.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    @Transactional
    public void setLike(Long postId, User user) {
        // 게시글 찾기
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Likes clickedLike = likeRepository.findByPostAndUser(post, user);
        if (clickedLike==null){
            post.addLike();
            Likes like = new Likes(post, user);
            likeRepository.save(like);
        } else{
            post.withdrawLike();
            likeRepository.delete(clickedLike);
        }
    }
}
