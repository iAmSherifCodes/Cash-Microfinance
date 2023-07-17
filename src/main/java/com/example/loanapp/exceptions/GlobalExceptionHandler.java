package com.example.loanapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<ExceptionDto> customerNotFoundExceptionHandler(CustomerNotFound ex, WebRequest wb){
        ExceptionDto exceptionDto = new ExceptionDto();

        exceptionDto.setDate(new Date());
        exceptionDto.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionDto.setMessage(ex.getMessage());

        return new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
    }

}
