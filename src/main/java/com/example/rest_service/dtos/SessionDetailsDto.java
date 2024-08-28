package com.example.rest_service.dtos;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDetailsDto {

    @NotBlank(message = "Username is required!")
    @Size(min = 3, message = "Username must have atleast 3 characters!")
    @Size(max = 20, message = "Username can have have atmost 20 characters!")
    private String userName;

    @NotBlank(message = "password is required!")
    @Size(min = 8, message = "password must have 8 characters!")
    @Getter
    private String password;

}