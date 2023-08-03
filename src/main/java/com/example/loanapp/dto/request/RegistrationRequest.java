package com.example.loanapp.dto.request;


import com.example.loanapp.data.Enums.EmploymentClassification;
import com.example.loanapp.data.Enums.EmploymentStatus;
import com.example.loanapp.data.Enums.Sex;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter @Getter
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private String phoneNumber;
    private String email;
    private Sex sex;
    private Integer age;
    private EmploymentStatus employmentStatus;
    private EmploymentClassification employmentClassification;
    private String nameOfCurrentEmployer;
    private BigDecimal basicMonthSalary;

}
