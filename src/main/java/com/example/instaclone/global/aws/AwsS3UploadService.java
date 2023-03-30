package com.example.instaclone.global.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@RequiredArgsConstructor
@Service
public class AwsS3UploadService implements com.example.instaclone.global.aws.UploadService {

    private final AmazonS3 amazonS3;
    private final com.example.instaclone.global.aws.S3Component component;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName){
        amazonS3.putObject(new PutObjectRequest(component.getBucket(), fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public String getFileUrl(String fileName){
        return amazonS3.getUrl(component.getBucket(), fileName).toString();
    }

    @Override
    public void deleteImage(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }
}
