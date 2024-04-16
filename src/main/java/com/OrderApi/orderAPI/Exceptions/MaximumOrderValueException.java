package com.OrderApi.orderAPI.Exceptions;

public class MaximumOrderValueException extends Exception{
    public MaximumOrderValueException(String message) {
        super(message);
    }
}
