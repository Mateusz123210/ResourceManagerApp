package com.example.resourcemanagerapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler({
         AuthorizedUserNotFoundException.class,
         UserNotPermittedException.class
    })
    public ResponseEntity handleForbidden(ApplicationException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler({
            ArgumentNotGivenException.class,
            InvalidArgumentException.class,
            ResourceNotFoundException.class,
            UserNotFoundException.class
    })
    public ResponseEntity handleBadRequest(ApplicationException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
