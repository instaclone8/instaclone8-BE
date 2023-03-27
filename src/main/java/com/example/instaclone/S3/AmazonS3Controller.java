package com.example.instaclone.S3;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.MultipartConfigElement;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class AmazonS3Controller {

    private final AwsS3Service awsS3Service;

//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        return new MultipartConfigElement("");
//    }
//    @Bean
//    public MultipartResolver multipartResolver() {
//        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(1000000);
//        return multipartResolver;
//    }

    //Amazon S3에 파일 업로드
    //@ApiOperation(value = "Amazon S3에 파일 업로드", notes = "Amazon S3에 파일 업로드 ")
    @PostMapping("/file")
    public ResponseEntity<List<String>> uploadFile(@RequestPart(value = "file", required = true) List<MultipartFile> multipartFile) {
        return ResponseEntity.ok().body(awsS3Service.uploadFile(multipartFile));
    }

    //Amazon S3에 업로드 된 파일을 삭제
    //@ApiOperation(value = "Amazon S3에 업로드 된 파일을 삭제", notes = "Amazon S3에 업로드된 파일 삭제")
    @DeleteMapping("/file")
    public ResponseEntity<Void> deleteFile(@RequestParam(name = "fileName", required = true) String fileName) {
        awsS3Service.deleteFile(fileName);
        return ResponseEntity.ok().build();
    }
}