package com.example.instaclone.post.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.instaclone.aws.UploadService;
import com.example.instaclone.post.dto.PostRequestDto;
import com.example.instaclone.post.dto.PostResponseDto;
import com.example.instaclone.post.dto.PostResponseDtoImpl;
import com.example.instaclone.post.entity.Post;
import com.example.instaclone.post.repository.PostRepository;
import com.example.instaclone.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final UploadService s3Service;
    private final PostRepository postRepository;

    @Transactional
    public void createPost(String content, MultipartFile image, User user){
        String fileName = createFileName(image);
        extensionCheck(fileName);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        uploadToS3Service(objectMetadata, image, fileName);

        String url = s3Service.getFileUrl(fileName);
        log.info(fileName, url, content);
        Post post = new Post(content, url, fileName, user);
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
    public void updatePost(Long postId, MultipartFile image, String content, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        if(!user.getId().equals(post.getUser().getId())){
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }
        s3Service.deleteImage(post.getImageName());
        log.info(post.getImageName());

        String fileName = createFileName(image.getOriginalFilename());
        extensionCheck(fileName);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        uploadToS3Service(objectMetadata, image, fileName);

        String url = s3Service.getFileUrl(fileName);
        log.info(fileName, url, content);

        post.updatePost(url, fileName, content);
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

        s3Service.deleteImage(post.getImageName());
        log.info(post.getImageName());
        postRepository.delete(post);
    }

    // 기존 확장자명 유지, 유니크한 파일의 이름을 생성하는 로직
    private String createFileName(String originalFileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    // 파일의 확장자명을 가져오는 로직
    private String getFileExtension(String fileName){
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        }catch (StringIndexOutOfBoundsException e){
            throw new IllegalArgumentException(String.format("잘못된 형식의 파일 (%s) 입니다.", fileName));
        }
    }
    private String createFileName(MultipartFile image){
        return image.getOriginalFilename();
    }

    private void extensionCheck(String fileName){
        String ext = fileName.substring(fileName.lastIndexOf(".")).substring(1);
        if (!(ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("jpeg"))) {
            throw new IllegalArgumentException("이미지 파일이 아닙니다.");
        }
    }

    private void uploadToS3Service(ObjectMetadata objectMetadata, MultipartFile image, String fileName) {
        objectMetadata.setContentLength(image.getSize());
        objectMetadata.setContentType((image.getContentType()));
        try (InputStream inputStream = image.getInputStream()) {
            s3Service.uploadFile(inputStream, objectMetadata, fileName);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생하였습니다. (%s)", image.getOriginalFilename()));
        }
    }
}
