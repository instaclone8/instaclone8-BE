package com.example.instaclone.post.service;

import com.example.instaclone.post.dto.PostRequestDto;
import com.example.instaclone.post.dto.PostResponseDto;
import com.example.instaclone.post.entity.Post;
import com.example.instaclone.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public void createPost(PostRequestDto reqDto) {
        Post post = new Post(reqDto);
        postRepository.save(post);
    }

    public List<PostResponseDto> getPosts(){
        List<PostResponseDto> resDtoList = new ArrayList<>();
        List<Post> postList = postRepository.findAll();
        for(Post post : postList){
            resDtoList.add(new PostResponseDto(post));
        }
        return resDtoList;
    }

    public PostResponseDto lookupPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return new PostResponseDto(post);
    }

    public void updatePost(PostRequestDto reqDto, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        post.updatePost(reqDto);
        postRepository.save(post);
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        postRepository.delete(post);
    }
}