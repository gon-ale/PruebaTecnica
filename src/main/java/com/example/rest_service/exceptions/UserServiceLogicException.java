package com.example.rest_service.exceptions;

public class UserServiceLogicException extends Exception {
    public UserServiceLogicException() {
        super("Something went wrong. Please try again later!");
    }
}