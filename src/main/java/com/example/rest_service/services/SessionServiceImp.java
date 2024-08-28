package com.example.rest_service.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.rest_service.dtos.ApiResponseDto;
import com.example.rest_service.dtos.ApiResponseStatus;
import com.example.rest_service.dtos.SessionDetailsDto;
import com.example.rest_service.exceptions.UserAlreadyExistsException;
import com.example.rest_service.exceptions.UserServiceLogicException;
import com.example.rest_service.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
@Slf4j
public class SessionServiceImp implements SessionService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<ApiResponseDto<?>> validateSession(SessionDetailsDto newSessionDetailsDto)
            throws UserAlreadyExistsException, UserServiceLogicException {
        try {
            if (userRepository.findByUsername(newSessionDetailsDto.getUserName()) == null) {
                throw new UserAlreadyExistsException(
                        "Login failed: User doesn't exists with username " + newSessionDetailsDto.getUserName());
            }

            String secret = "Secreto1234";

            Jwts
            .builder()
            .claim("username", newSessionDetailsDto.getUserName())
            .setSubject(secret)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+100*60*60*60))
            .signWith(SignatureAlgorithm.HS256,secret)
            .compact();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User account deleted successfully!")); 
        } catch (Exception e) {
            log.error("Failed to delete user account: " + e.getMessage());
            throw new UserServiceLogicException();
        }
    }
}
