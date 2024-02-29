package com.OrderApi.orderAPI.exception;

public class UserNotExistsException extends Exception{
    public UserNotExistsException(String message) {
        super(message);
    }
}
