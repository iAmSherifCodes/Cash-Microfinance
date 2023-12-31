package com.example.loanapp.service;


import com.example.loanapp.dto.request.*;
import com.example.loanapp.dto.response.*;
import com.github.fge.jsonpatch.JsonPatch;

public interface CustomerService {
    MessageResponse register(RegistrationRequest registrationRequest);
    LoginResponse login(LoginRequest loginRequest);
//    MessageResponse updateCustomerDetails(Long id, RegistrationRequest request);
    LoanApplicationResponse applyForLoan(LoanApplicationRequest loanApplicationRequest);
    ApplicationStatusResponse viewLoanApplicationStatus(ApplicationStatusRequest applicationStatusRequest);
    LoanAgreementResponse viewLoanAgreement(LoanAgreementRequest loanAgreementRequest);
    MessageResponse updateCustomerDetails(Long id, RegistrationRequest request);
}
