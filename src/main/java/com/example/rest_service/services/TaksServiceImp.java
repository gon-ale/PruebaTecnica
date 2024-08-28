package com.example.rest_service.services;

import java.time.LocalDateTime;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.rest_service.dtos.ApiResponseDto;
import com.example.rest_service.dtos.ApiResponseStatus;
import com.example.rest_service.dtos.TaskDetailsRequestDto;
import com.example.rest_service.dtos.TaskDetailsUpdateDto;
import com.example.rest_service.exceptions.UserAlreadyExistsException;
import com.example.rest_service.exceptions.notFoundException;
import com.example.rest_service.exceptions.UserServiceLogicException;
import com.example.rest_service.models.Task;
import com.example.rest_service.repository.TaskRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j

public class TaksServiceImp implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public ResponseEntity<ApiResponseDto<?>> registerTask(TaskDetailsRequestDto newTaskDetails)
            throws UserAlreadyExistsException, UserServiceLogicException {
        try {
            if (taskRepository.findByName(newTaskDetails.getName()) != null) {
                throw new UserAlreadyExistsException(
                        "Registration failed: Task already exists with username " + newTaskDetails.getName());
            }

            Task newTask = new Task(
                    newTaskDetails.getName(), "No Resuelto", newTaskDetails.getDescription(), LocalDateTime.now());
            System.out.println(newTask);
            // save() is an in built method given by JpaRepository
            taskRepository.save(newTask);
            System.out.println(taskRepository.findByName(newTaskDetails.getName()));
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

    public ResponseEntity<ApiResponseDto<?>> getAllTasks() throws UserServiceLogicException {
        try {
            List<Task> tasks = taskRepository.findAllByOrderByCreationDateDesc();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), tasks));

        } catch (Exception e) {
            log.error("Failed to fetch all users: " + e.getMessage());
            throw new UserServiceLogicException();
        }
    };

    public ResponseEntity<ApiResponseDto<?>> updateTask(TaskDetailsUpdateDto newTaskDetails, int id)
            throws NotFoundException, UserServiceLogicException {
        try {
            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new notFoundException("Task not found with id " + id));

            task.setState(newTaskDetails.getState());
            task.setUpdateDate(LocalDateTime.now());
            taskRepository.save(task);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Task updated successfully!"));

        } catch (Exception e) {
            log.error("Failed to delete user account: " + e.getMessage());
            throw new UserServiceLogicException();
        }
    };

    public ResponseEntity<ApiResponseDto<?>> deleteTask(int id)
            throws UserServiceLogicException, NotFoundException {
        try {
            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new notFoundException("Task not found with id " + id));

            taskRepository.delete(task);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Task account deleted successfully!"));
        } catch (Exception e) {
            log.error("Failed to delete user account: " + e.getMessage());
            throw new UserServiceLogicException();
        }
    }
}
