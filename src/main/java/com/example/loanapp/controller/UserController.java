package com.example.loanapp.controller;

import com.example.loanapp.data.model.Customer;
import com.example.loanapp.dto.request.LoanApplicationRequest;
import com.example.loanapp.dto.request.LoginRequest;
import com.example.loanapp.dto.request.OfficerLoginRequest;
import com.example.loanapp.dto.request.RegistrationRequest;
import com.example.loanapp.dto.response.*;
import com.example.loanapp.service.CustomerService;
import com.example.loanapp.service.LoanAppOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final CustomerService customerService;

    @Autowired
    public UserController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody RegistrationRequest registrationRequest){
        return new ResponseEntity<>(customerService.register(registrationRequest), HttpStatus.OK);
    }

    @PostMapping("/apply")
    public ResponseEntity<LoanApplicationResponse> applyForLoanController (@RequestBody LoanApplicationRequest loanApplicationRequest){
        return new ResponseEntity<>(customerService.applyForLoan(loanApplicationRequest), HttpStatus.OK);
    }


    @GetMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request){
        return new ResponseEntity<>(customerService.login(request), HttpStatus.OK);
    }


}
