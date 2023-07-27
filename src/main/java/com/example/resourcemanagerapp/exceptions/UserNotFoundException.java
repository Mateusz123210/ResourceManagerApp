package com.example.resourcemanagerapp.exceptions;

public class UserNotFoundException extends ApplicationException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
