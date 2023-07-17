package com.example.loanapp.exceptions;

import java.io.Serial;

public class LoanNotFound extends RuntimeException{
    @Serial
    private static final long serialVersionUID =1;
    public LoanNotFound(String message){
        super(message);
    }
}