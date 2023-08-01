package com.example.loanapp.controller;

import com.example.loanapp.data.model.Customer;
import com.example.loanapp.dto.request.OfficerLoginRequest;
import com.example.loanapp.dto.request.ReviewLoanRequest;
import com.example.loanapp.dto.request.UpdateLoanStatusRequest;
import com.example.loanapp.dto.response.*;
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
    public ResponseEntity<CustomerListResponse> allCustomers(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ){
        return new ResponseEntity<>(loanAppOfficerService.customers(pageNo, pageSize), HttpStatus.OK);
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
