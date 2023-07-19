package com.example.loanapp.dto.request;

import com.example.loanapp.data.Enums.LoanStatus;
import lombok.Data;

@Data
public class UpdateLoanStatusRequest {
    private String officerName;
    private String userEmail;
    private LoanStatus loanStatus;
}
