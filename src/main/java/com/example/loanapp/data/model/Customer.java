package com.example.loanapp.data.model;

import com.example.loanapp.data.Enums.EmploymentClassification;
import com.example.loanapp.data.Enums.EmploymentStatus;
import com.example.loanapp.data.Enums.Sex;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;
    @Enumerated(EnumType.STRING)
    private EmploymentClassification employmentClassification;
    private String nameOfCurrentEmployer;
    private BigDecimal basicMonthSalary;
    @JoinColumn(name = "CustomerLoan")
    @OneToOne
    private Loan loan;
}
