package com.example.resourcemanagerapp.exceptions;

public class AuthorizedUserNotFoundException extends ApplicationException{
    public AuthorizedUserNotFoundException(String message) {
        super(message);
    }
}
