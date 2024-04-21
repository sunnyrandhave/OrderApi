package com.OrderApi.orderAPI.Exceptions;

public class PromoCodeExpiredException extends Exception{
    public PromoCodeExpiredException(String message) {
        super(message);
    }
}
