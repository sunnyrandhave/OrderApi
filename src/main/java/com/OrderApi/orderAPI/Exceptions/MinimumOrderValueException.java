package com.OrderApi.orderAPI.Exceptions;

public class MinimumOrderValueException extends Exception{
    public MinimumOrderValueException(String message) {
        super(message);
    }
}
