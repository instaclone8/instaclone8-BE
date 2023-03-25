//package com.example.instaclone.like.controller;
//
//import com.example.instaclone.like.service.LikeService;
//import com.example.instaclone.security.UserDetailsImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.spraingframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequestMapping("/api/posts")
//@RequiredArgsConstructor
//@Controller
//public class LikeController {
//
//    private final LikeService likeService;
//
//    @PostMapping("/{postId}/like")
//    public void setLike(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        likeService.setLike(postId, userDetails.getUser());
//    }
//}
