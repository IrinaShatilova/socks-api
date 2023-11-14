package com.example.stock.controller;

import com.example.stock.dto.Message;
import com.example.stock.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class AdviceController {


    @ExceptionHandler
    public ResponseEntity<Message> handleApiException(ApiException apiException) {
        return new ResponseEntity<>(new Message(apiException.getMessage()), HttpStatus.valueOf(apiException.getStatus()));
    }


}
