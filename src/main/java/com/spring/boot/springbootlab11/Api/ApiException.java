package com.spring.boot.springbootlab11.Api;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
