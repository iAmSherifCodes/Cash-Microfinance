package com.example.loanapp.controller;

import com.example.loanapp.data.model.Customer;
import com.example.loanapp.dto.request.OfficerLoginRequest;
import com.example.loanapp.dto.request.ReviewLoanRequest;
import com.example.loanapp.dto.request.UpdateLoanStatusRequest;
import com.example.loanapp.dto.response.ReviewLoanResponse;
import com.example.loanapp.dto.response.OfficerLoginResponse;
import com.example.loanapp.dto.response.UpdateLoanStatusResponse;
import com.example.loanapp.dto.response.ViewLoanApplicationsDto;
import com.example.loanapp.service.LoanAppOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/officer")
public class OfficerController {

    private final LoanAppOfficerService loanAppOfficerService;

    @Autowired
    public OfficerController(LoanAppOfficerService loanAppOfficerService) {
        this.loanAppOfficerService = loanAppOfficerService;
    }
    @GetMapping("/view-all-loans")
    public ResponseEntity<List<ViewLoanApplicationsDto>> viewAllApplicationsDto(){
        return new ResponseEntity<>(loanAppOfficerService.viewLoanApplications(), HttpStatus.OK);
    }

    @PostMapping("/officer-login")
    public ResponseEntity<OfficerLoginResponse> officerLoginController(@RequestBody OfficerLoginRequest officerLoginRequest){
        return new ResponseEntity<>(loanAppOfficerService.login(officerLoginRequest), HttpStatus.OK);
    }

    @GetMapping("/all-customers")
    public ResponseEntity<List<Customer>> allCustomers(){
        return new ResponseEntity<>(loanAppOfficerService.customers(), HttpStatus.OK);
    }

    @GetMapping("/review-loan")
    public ResponseEntity<ReviewLoanResponse> reviewLoanApplicationById(@RequestBody ReviewLoanRequest request){
        return new ResponseEntity<>(this.loanAppOfficerService.reviewLoanApplication(request), HttpStatus.OK);
    }

    @PostMapping("/update-loan-status")
    public ResponseEntity<UpdateLoanStatusResponse> updateLoanStatusRequestResponseEntity(@RequestBody UpdateLoanStatusRequest request){
        return new ResponseEntity<>(this.loanAppOfficerService.updateLoanStatus(request), HttpStatus.OK);
    }


}
