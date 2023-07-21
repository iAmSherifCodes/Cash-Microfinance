package com.example.loanapp.data.model;

import com.example.loanapp.data.Enums.LoanStatus;
import com.example.loanapp.data.Enums.RepaymentPreferences;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Loan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private BigDecimal loanAmount;
    private String loanPurpose;
    private Integer tenureInMonths;
    @Enumerated(EnumType.STRING)
    private RepaymentPreferences repaymentPreference;
    @Enumerated(EnumType.STRING)
    private LoanStatus loanApplicationStatus = LoanStatus.IN_PROGRESS;
    private LocalDate loanStartDate = LocalDate.now();
//    private LocalDate loanEndDate;
    private BigDecimal amountPerPaymentPeriod;
    @OneToOne
    private LoanOfficer loanOfficer;

}
