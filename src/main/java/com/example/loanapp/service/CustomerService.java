package com.example.loanapp.service;


import com.example.loanapp.dto.request.ApplicationStatusRequest;
import com.example.loanapp.dto.request.LoanApplicationRequest;
import com.example.loanapp.dto.request.LoginRequest;
import com.example.loanapp.dto.request.RegistrationRequest;
import com.example.loanapp.dto.response.ApplicationStatusResponse;
import com.example.loanapp.dto.response.LoanApplicationResponse;
import com.example.loanapp.dto.response.LoginResponse;
import com.example.loanapp.dto.response.RegistrationResponse;

public interface CustomerService {
    RegistrationResponse register(RegistrationRequest registrationRequest);
    LoginResponse login(LoginRequest loginRequest);
    LoanApplicationResponse applyForLoan(LoanApplicationRequest loanApplicationRequest);
    ApplicationStatusResponse viewLoanApplicationStatus(ApplicationStatusRequest applicationStatusRequest);
    String viewLoanAgreement();
}
