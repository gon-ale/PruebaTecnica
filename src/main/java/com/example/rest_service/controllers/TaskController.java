package com.example.rest_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest_service.dtos.ApiResponseDto;
import com.example.rest_service.dtos.TaskDetailsRequestDto;
import com.example.rest_service.dtos.TaskDetailsUpdateDto;
import com.example.rest_service.exceptions.UserAlreadyExistsException;
import com.example.rest_service.exceptions.UserServiceLogicException;
import com.example.rest_service.services.TaskService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    public TaskService taskService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponseDto<?>> registerTask(
            @Valid @RequestBody TaskDetailsRequestDto taskDetailsRequestDto)
            throws UserAlreadyExistsException, UserServiceLogicException {
        return taskService.registerTask(taskDetailsRequestDto);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponseDto<?>> getAllTasks() throws UserServiceLogicException {
        return taskService.getAllTasks();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto<?>> updateUser(@Valid @RequestBody TaskDetailsUpdateDto taskDetailsUpdateDto,
            @PathVariable int id)
            throws NotFoundException, UserServiceLogicException {
        return taskService.updateTask(taskDetailsUpdateDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto<?>> deleteUser(@PathVariable int id)
            throws NotFoundException, UserServiceLogicException {
        return taskService.deleteTask(id);
    }

}
