package com.example.loanapp.dto.response;

import com.example.loanapp.data.Enums.LoanStatus;
import com.example.loanapp.data.model.LoanOfficer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ViewLoanApplicationsDto {
    private Long Id;
    private BigDecimal loanAmount;
    private String loanPurpose;
    private int tenureInMonths;
    private LoanStatus loanApplicationStatus;
    private LocalDate dateTime;
    private LoanOfficer loanOfficer;
}
