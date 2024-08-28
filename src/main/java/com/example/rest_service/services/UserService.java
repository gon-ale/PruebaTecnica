package com.example.rest_service.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rest_service.dtos.ApiResponseDto;
import com.example.rest_service.dtos.UserDetailsRequestDto;
import com.example.rest_service.exceptions.UserAlreadyExistsException;
import com.example.rest_service.exceptions.notFoundException;
import com.example.rest_service.exceptions.UserServiceLogicException;

@Service
public interface UserService {
    ResponseEntity<ApiResponseDto<?>> registerUser(UserDetailsRequestDto newUserDetails)
            throws UserAlreadyExistsException, UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> deleteUser(int id)
            throws UserServiceLogicException, notFoundException;

}
