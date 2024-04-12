package com.OrderApi.orderAPI.controllerAdvice;


import com.OrderApi.orderAPI.Exceptions.InvalidPhoneNumberException;
import com.OrderApi.orderAPI.Exceptions.UserNumberAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNumberAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserNumberAlreadyExistsException userNumberAlreadyExistsException){
        return new ResponseEntity<>(userNumberAlreadyExistsException.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<String> handleInvalidNumberException(InvalidPhoneNumberException InvalidPhoneNumberException){
        return new ResponseEntity<>(InvalidPhoneNumberException.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }


}
