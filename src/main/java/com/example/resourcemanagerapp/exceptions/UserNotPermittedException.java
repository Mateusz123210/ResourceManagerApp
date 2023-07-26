package com.example.resourcemanagerapp.exceptions;

public class UserNotPermittedException extends ApplicationException{
    public UserNotPermittedException(String message) {
        super(message);
    }
}
