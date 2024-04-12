package com.OrderApi.orderAPI.Exceptions;

public class UserNumberAlreadyExistsException extends Exception{
    public UserNumberAlreadyExistsException(String message) {
        super(message);
    }
}
