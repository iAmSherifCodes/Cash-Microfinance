package com.example.loanapp.dto.request;

import com.example.loanapp.data.Enums.RepaymentPreferences;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanApplicationRequest {
    private String email;
    private BigDecimal loanAmount;
    private String loanPurpose;
    private Integer tenureInMonths;
    private BigDecimal amountPerPaymentPeriod;
    private RepaymentPreferences repaymentPreference;
//    private LocalDate loanEndDate;
}
