package com.example.instaclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableJpaAuditing
@SpringBootApplication
public class InstacloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstacloneApplication.class, args);
    }

}
