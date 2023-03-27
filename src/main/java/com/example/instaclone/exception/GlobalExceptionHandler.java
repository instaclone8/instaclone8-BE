package com.example.instaclone.exception;

import com.example.instaclone.user.dto.MessageResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //    throw로 발생시킨 오류 처리
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageResponseDto> handleRuntimeException(RuntimeException e){
        String message = e.getMessage().split(":")[0];
        MessageResponseDto messageResponseDto = new MessageResponseDto(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(messageResponseDto, HttpStatus.BAD_REQUEST);
    }

    //    @Valid 오류 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponseDto> handleNotValidException(MethodArgumentNotValidException e){
        String message = e.getFieldError().getDefaultMessage().split(":")[0];
        MessageResponseDto messageResponseDto = new MessageResponseDto(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(messageResponseDto, HttpStatus.BAD_REQUEST);
    }

    //    나머지 예외 처리
    @ExceptionHandler
    public ResponseEntity<MessageResponseDto> handleException(Exception e){
        String message = e.getMessage().split(":")[0];
        MessageResponseDto messageResponseDto = new MessageResponseDto(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(messageResponseDto, HttpStatus.BAD_REQUEST);
    }

    //파일 업로드 용량 초과시 발생
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    protected ResponseEntity<MessageResponseDto> handleMaxUploadSizeExceededException(
            MaxUploadSizeExceededException e) {
        String message = e.getMessage().split(":")[0];
        MessageResponseDto messageResponseDto = new MessageResponseDto(HttpStatus.PAYLOAD_TOO_LARGE, message);
        return new ResponseEntity<>(messageResponseDto, HttpStatus.PAYLOAD_TOO_LARGE);
    }
}
