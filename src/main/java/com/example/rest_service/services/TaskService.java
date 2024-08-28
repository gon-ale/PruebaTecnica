package com.example.rest_service.services;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rest_service.dtos.ApiResponseDto;
import com.example.rest_service.dtos.TaskDetailsRequestDto;
import com.example.rest_service.dtos.TaskDetailsUpdateDto;
import com.example.rest_service.exceptions.UserAlreadyExistsException;
import com.example.rest_service.exceptions.UserServiceLogicException;

@Service
public interface TaskService {
      ResponseEntity<ApiResponseDto<?>> registerTask(TaskDetailsRequestDto newUserDetails)
                  throws UserAlreadyExistsException, UserServiceLogicException;

      ResponseEntity<ApiResponseDto<?>> getAllTasks()
                  throws UserServiceLogicException;

      ResponseEntity<ApiResponseDto<?>> updateTask(TaskDetailsUpdateDto newUserDetails, int id)
                  throws NotFoundException, UserServiceLogicException;

      ResponseEntity<ApiResponseDto<?>> deleteTask(int id)
                  throws UserServiceLogicException, NotFoundException;
}
