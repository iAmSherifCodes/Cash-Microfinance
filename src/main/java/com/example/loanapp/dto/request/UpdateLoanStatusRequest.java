package com.example.loanapp.dto.request;

import com.example.loanapp.data.Enums.LoanStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UpdateLoanStatusRequest {
    private Long officerId;
//    private String userEmail;
    private Long loanId;
    private LoanStatus loanStatus;
}
