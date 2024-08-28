package com.example.rest_service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDetailsUpdateDto {
    @NotBlank(message = "state is required!")
    @Size(min = 3, message = "description must have at least 3 charcaters!")
    @Size(max = 50, message = "description cant have atmost 50 characters!")
    private String state;

}
