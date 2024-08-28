package com.example.rest_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ApiResponseDto<T> {
    private String status;
    private T response;
}
