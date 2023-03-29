package com.example.instaclone.global.aws;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@ConfigurationProperties(prefix = "cloud.aws.s3")
@Component
@Getter
public class S3Component {

    // @Value("${cloud.aws.s3.bucket}")로도 가능
    private String bucket;

}