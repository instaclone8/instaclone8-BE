package com.example.instaclone.post.service;

import com.example.instaclone.post.dto.PostRequestDto;
import com.example.instaclone.post.dto.PostResponseDto;
import com.example.instaclone.post.dto.PostResponseDtoImpl;
import com.example.instaclone.post.entity.Post;
import com.example.instaclone.post.repository.PostRepository;
import com.example.instaclone.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void createPost(PostRequestDto reqDto, User user) {
        Post post = new Post(reqDto, user);
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts(){
        List<PostResponseDto> resDtoList = new ArrayList<>();
        List<Post> postList = postRepository.findAll();
        for(Post post : postList){
            resDtoList.add(new PostResponseDto(post));
        }
        return resDtoList;
    }

    @Transactional(readOnly = true)
    public PostResponseDtoImpl lookupPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return new PostResponseDtoImpl(post);

    }

    @Transactional
    public void updatePost(PostRequestDto reqDto, Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        if(!user.getId().equals(post.getUser().getId())){
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }
        post.updatePost(reqDto);
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        if(!user.getId().equals(post.getUser().getId())){
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }
        postRepository.delete(post);
    }
}

