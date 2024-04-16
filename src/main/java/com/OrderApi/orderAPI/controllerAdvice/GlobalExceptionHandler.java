package com.OrderApi.orderAPI.controllerAdvice;


import com.OrderApi.orderAPI.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    @ExceptionHandler(MinimumOrderValueException.class)
    public ResponseEntity<String> handleOrderValueMinimumException(MinimumOrderValueException minimumOrderValueException){
        return new ResponseEntity<>(minimumOrderValueException.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException orderNotFoundException){
        return new ResponseEntity<>(orderNotFoundException.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidOrderStatusException.class)
    public ResponseEntity<String> handleInvalidOrderStatusException(InvalidOrderStatusException invalidOrderStatusException){
        return new ResponseEntity<>(invalidOrderStatusException.getMessage(),HttpStatus.BAD_REQUEST);
    }



}
