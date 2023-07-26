package com.example.resourcemanagerapp.exceptions;

public class ResourceNotFoundException extends ApplicationException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
