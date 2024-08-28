package com.example.rest_service.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.rest_service.dtos.ApiResponseDto;
import com.example.rest_service.dtos.ApiResponseStatus;
import com.example.rest_service.dtos.SessionDetailsDto;
import com.example.rest_service.exceptions.UserServiceLogicException;
import com.example.rest_service.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SessionServiceImp implements SessionService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Override
    public ResponseEntity<ApiResponseDto<?>> login(SessionDetailsDto sessionDetailsDto)
            throws UserServiceLogicException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(sessionDetailsDto.getUserName(), sessionDetailsDto.getPassword()));
            UserDetails user = userRepository.findByUsername(sessionDetailsDto.getUserName());
            System.out.println("User : "+user.getUsername());
            String token = jwtService.getToken(user);
            System.out.println("Token : "+token);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(),
                            "Logged Succesfully! "+token));

        } catch (Exception e) {
            System.out.println(e);
            throw new UserServiceLogicException();
        }
    }
}
