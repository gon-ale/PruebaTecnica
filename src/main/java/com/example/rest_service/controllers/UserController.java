package com.example.rest_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest_service.dtos.ApiResponseDto;
import com.example.rest_service.dtos.UserDetailsRequestDto;
import com.example.rest_service.exceptions.UserAlreadyExistsException;
import com.example.rest_service.exceptions.notFoundException;
import com.example.rest_service.exceptions.UserServiceLogicException;
import com.example.rest_service.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userservice;

    @PostMapping("new")
    public ResponseEntity<ApiResponseDto<?>> registerUser(
            @Valid @RequestBody UserDetailsRequestDto userDetailsRequestDto)
            throws UserAlreadyExistsException, UserServiceLogicException {
        return userservice.registerUser(userDetailsRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto<?>> deleteUser(@PathVariable int id)
            throws notFoundException, UserServiceLogicException {
        return userservice.deleteUser(id);
    }

}
