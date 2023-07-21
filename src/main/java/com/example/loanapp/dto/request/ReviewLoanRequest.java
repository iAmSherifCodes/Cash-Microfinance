package com.example.loanapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ReviewLoanRequest {
    private String customerEmail;
}
