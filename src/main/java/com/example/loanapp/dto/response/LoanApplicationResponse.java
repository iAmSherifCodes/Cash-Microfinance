package com.example.loanapp.dto.response;

import com.example.loanapp.data.Enums.RepaymentPreferences;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanApplicationResponse {
    private BigDecimal loanAmount;
    private String loanPurpose;
    private int tenureInMonths;
    private String message;
    private RepaymentPreferences repaymentPreference;
}
