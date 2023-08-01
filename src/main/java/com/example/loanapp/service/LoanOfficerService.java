package com.example.loanapp.service;


import com.example.loanapp.data.model.Customer;
import com.example.loanapp.dto.request.OfficerLoginRequest;
import com.example.loanapp.dto.request.ReviewLoanRequest;
import com.example.loanapp.dto.request.UpdateLoanStatusRequest;
import com.example.loanapp.dto.response.*;

import java.util.List;

public interface LoanOfficerService {
    OfficerLoginResponse login(OfficerLoginRequest officerLoginResponse);
    ReviewLoanResponse reviewLoanApplication(ReviewLoanRequest request);
    List<ViewLoanApplicationsDto> viewLoanApplications();
    CustomerListResponse customers(Integer pageNo, Integer pageSize);
    UpdateLoanStatusResponse updateLoanStatus(UpdateLoanStatusRequest request);



}