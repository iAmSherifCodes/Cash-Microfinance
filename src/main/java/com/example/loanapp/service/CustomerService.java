package com.example.loanapp.service;


import com.example.loanapp.dto.request.*;
import com.example.loanapp.dto.response.*;

public interface CustomerService {
    RegistrationResponse register(RegistrationRequest registrationRequest);
    LoginResponse login(LoginRequest loginRequest);
    LoanApplicationResponse applyForLoan(LoanApplicationRequest loanApplicationRequest);
    ApplicationStatusResponse viewLoanApplicationStatus(ApplicationStatusRequest applicationStatusRequest);
    LoanAgreementResponse viewLoanAgreement(LoanAgreementRequest loanAgreementRequest);
}
