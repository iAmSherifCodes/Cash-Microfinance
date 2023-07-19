package com.example.loanapp.dto.response;

import com.example.loanapp.data.Enums.EmploymentClassification;
import com.example.loanapp.data.Enums.EmploymentStatus;
import com.example.loanapp.data.Enums.LoanStatus;
import com.example.loanapp.data.Enums.Sex;
import com.example.loanapp.data.model.LoanOfficer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoanDto {
    private String customerFirstName;
    private String customerLastName;
    private String customerAddress;
    private String customerPhoneNumber;
    private String customerEmail;
    private Sex customerSex;
    private Integer customerAge;
    private EmploymentStatus customerEmploymentStatus;
    private EmploymentClassification customerEmploymentClassification;
    private String customerNameOfCurrentEmployer;
    private BigDecimal customerBasicMonthSalary;
    private BigDecimal customerLoanAmount;
    private Integer customerTenureInMonths;
    private LoanStatus loanApplicationStatus;
    private LocalDateTime dateTime;
    private LoanOfficer loanOfficer;
}
