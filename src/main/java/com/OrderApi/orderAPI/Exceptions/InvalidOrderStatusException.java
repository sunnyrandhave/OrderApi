package com.OrderApi.orderAPI.Exceptions;

public class InvalidOrderStatusException extends Exception{
    public InvalidOrderStatusException(String message) {
        super(message);
    }
}
