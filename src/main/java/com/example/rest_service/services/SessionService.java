package com.example.rest_service.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rest_service.dtos.ApiResponseDto;
import com.example.rest_service.dtos.SessionDetailsDto;
import com.example.rest_service.exceptions.UserServiceLogicException;

@Service
public interface SessionService {
    ResponseEntity<ApiResponseDto<?>> login(SessionDetailsDto sessionDetailsDto)
                  throws UserServiceLogicException;
}
