package com.example.rest_service.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.rest_service.dtos.ApiResponseDto;
import com.example.rest_service.dtos.ApiResponseStatus;
import com.example.rest_service.exceptions.UserAlreadyExistsException;
import com.example.rest_service.exceptions.notFoundException;
import com.example.rest_service.exceptions.UserServiceLogicException;

@RestControllerAdvice
public class UserServiceExceptionHandler {

    @ExceptionHandler(value = notFoundException.class)
    public ResponseEntity<ApiResponseDto<?>> NotFoundExceptionHandler(notFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDto<?>> UserAlreadyExistsExceptionHandler(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = UserServiceLogicException.class)
    public ResponseEntity<ApiResponseDto<?>> UserServiceLogicExceptionHandler(UserServiceLogicException exception) {
        return ResponseEntity.badRequest()
                .body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<?>> MethodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException exception) {

        List<String> errorMessage = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessage.add(error.getDefaultMessage());
        });
        return ResponseEntity.badRequest()
                .body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), errorMessage.toString()));
    }

}