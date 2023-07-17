package com.example.loanapp.dto.response;


import com.example.loanapp.data.model.Customer;
import lombok.Data;

@Data
public class LoginResponse {
    private Customer foundCustomer;
    private String errorMessage;
}
