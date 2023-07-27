package com.example.loanapp.exceptions;

import java.io.Serial;

public class LoanApplicationException extends RuntimeException{
    @Serial
    private static final long serialVersionUID =1;
    public LoanApplicationException(String message) {
        super(message);
    }
}
