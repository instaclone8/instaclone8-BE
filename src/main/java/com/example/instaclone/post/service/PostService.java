package com.example.instaclone.post.service;

import com.example.instaclone.post.dto.PostRequestDto;
import com.example.instaclone.post.dto.PostResponseDto;
import com.example.instaclone.post.dto.PostResponseDtoImpl;
import com.example.instaclone.post.entity.Post;
import com.example.instaclone.post.repository.PostRepository;
import com.example.instaclone.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void createPost(PostRequestDto reqDto, User user) {
        Post post = new Post(reqDto, user);
        postRepository.save(post);
    }

    //무한 스크롤 : 최신 순선 내림차순 10개 씩 잘라서 조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> fetchPages(Long lastPostId, int size){
        PageRequest pageRequest = PageRequest.of(0,10);
        Page<Post> entityPage = postRepository.findByIdLessThanOrderByIdDesc(lastPostId, pageRequest);
        List<Post> entityList = entityPage.getContent();
        return entityList.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    //최신 작성 순서 내림차순 조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts(){
        List<Post> posts = postRepository.findAllByOrderByCreatedateDesc();
        return posts.stream().map(PostResponseDto::new).toList();
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


