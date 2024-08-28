package com.example.rest_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest_service.dtos.ApiResponseDto;
import com.example.rest_service.dtos.SessionDetailsDto;
import com.example.rest_service.exceptions.UserServiceLogicException;
import com.example.rest_service.services.SessionService;

import jakarta.validation.Valid;

@RestController
public class SessionController {
    @Autowired
    public SessionService sessionService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<?>> login(
            @Valid @RequestBody SessionDetailsDto sessionDetailsDto)
            throws UserServiceLogicException {
        return sessionService.login(sessionDetailsDto);
    }
}
