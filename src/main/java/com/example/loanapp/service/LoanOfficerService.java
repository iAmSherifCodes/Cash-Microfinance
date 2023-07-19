package com.example.loanapp.service;


import com.example.loanapp.data.model.Customer;
import com.example.loanapp.dto.request.OfficerLoginRequest;
import com.example.loanapp.dto.request.UpdateLoanStatusRequest;
import com.example.loanapp.dto.response.LoanDto;
import com.example.loanapp.dto.response.OfficerLoginResponse;
import com.example.loanapp.dto.response.ViewLoanApplicationsDto;

import java.util.List;

public interface LoanOfficerService {
    OfficerLoginResponse login(OfficerLoginRequest officerLoginResponse);
    LoanDto reviewLoanApplication(Long loanApplicationId);
    List<ViewLoanApplicationsDto> viewLoanApplications();
    List<Customer> customers();
    void updateLoanStatus(UpdateLoanStatusRequest request);



}