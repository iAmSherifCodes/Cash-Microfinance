package com.example.loanapp.dto.response;

import com.example.loanapp.data.Enums.EmploymentClassification;
import com.example.loanapp.data.Enums.EmploymentStatus;
import com.example.loanapp.data.Enums.Sex;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegistrationResponse {
    private String message;
}
