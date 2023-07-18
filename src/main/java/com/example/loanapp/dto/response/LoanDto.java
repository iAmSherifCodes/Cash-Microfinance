package com.example.loanapp.dto.response;

import com.example.loanapp.data.Enums.LoanStatus;
import com.example.loanapp.data.model.LoanOfficer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoanDto {
    private BigDecimal loanAmount;
    private Integer tenureInMonths;
    private LoanStatus loanApplicationStatus;
    private LocalDateTime dateTime;
    private LoanOfficer loanOfficer;
}
