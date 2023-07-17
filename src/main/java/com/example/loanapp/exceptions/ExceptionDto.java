package com.example.loanapp.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionDto {
    private Date date;
    private String message;
    private Integer statusCode;
}
