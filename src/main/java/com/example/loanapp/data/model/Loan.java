package com.example.loanapp.data.model;

import com.example.loanapp.data.Enums.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Loan {
    @Id
    private Long Id;
    private BigDecimal loanAmount;
    private String loanPurpose;
    private Integer tenureInMonths;
    @Enumerated(EnumType.STRING)
    private LoanStatus loanApplicationStatus = LoanStatus.IN_PROGRESS;
    private LocalDateTime dateTime = LocalDateTime.now();
    @OneToOne
    private LoanOfficer loanOfficer;


}
