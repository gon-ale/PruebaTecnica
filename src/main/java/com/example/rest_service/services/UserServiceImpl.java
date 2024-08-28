package com.example.rest_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.rest_service.dtos.ApiResponseDto;
import com.example.rest_service.dtos.ApiResponseStatus;
import com.example.rest_service.dtos.UserDetailsRequestDto;
import com.example.rest_service.exceptions.UserAlreadyExistsException;
import com.example.rest_service.exceptions.notFoundException;
import com.example.rest_service.exceptions.UserServiceLogicException;
import com.example.rest_service.models.User;
import com.example.rest_service.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponseDto<?>> registerUser(UserDetailsRequestDto newUserDetails)
            throws UserAlreadyExistsException, UserServiceLogicException {

        try {
            if (userRepository.findByUsername(newUserDetails.getUserName()) != null) {
                throw new UserAlreadyExistsException(
                        "Registration failed: User already exists with username " + newUserDetails.getUserName());
            }

            User newUser = new User(
                    newUserDetails.getUserName(), newUserDetails.getName(), newUserDetails.getPassword());

            // save() is an in built method given by JpaRepository
            userRepository.save(newUser);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(),
                            "New user account has been successfully created!"));

        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to create new user account: " + e.getMessage());
            throw new UserServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> deleteUser(int id) throws UserServiceLogicException, notFoundException {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new notFoundException("User not found with id " + id));

            userRepository.delete(user);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User account deleted successfully!"));
        } catch (notFoundException e) {
            throw new notFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to delete user account: " + e.getMessage());
            throw new UserServiceLogicException();
        }
    }
}