package com.example.stock.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{
    private Integer status;

    public ApiException(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public ApiException(String message) {
        super(message);
    }

}
