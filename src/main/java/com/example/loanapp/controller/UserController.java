package com.example.loanapp.controller;

import com.example.loanapp.data.model.Customer;
import com.example.loanapp.dto.request.*;
import com.example.loanapp.dto.response.*;
import com.example.loanapp.service.CustomerService;
import com.example.loanapp.service.LoanAppOfficerService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final CustomerService customerService;

    @Autowired
    public UserController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody RegistrationRequest registrationRequest){
        return new ResponseEntity<>(customerService.register(registrationRequest), HttpStatus.OK);
    }

    @PostMapping("/apply")
    public ResponseEntity<LoanApplicationResponse> applyForLoanController (@RequestBody LoanApplicationRequest loanApplicationRequest){
        return new ResponseEntity<>(customerService.applyForLoan(loanApplicationRequest), HttpStatus.OK);
    }

    @PatchMapping("update-customer/{id}")
    public ResponseEntity<MessageResponse> updateCustomerDetails(@RequestBody RegistrationRequest request, @PathVariable Long id){
        return new ResponseEntity<>(customerService.updateCustomerDetails(id, request), HttpStatus.OK);
    }

//@RequestMapping(value = "/update-customer/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
//public ResponseEntity<MessageResponse> updateCustomerDetails(@RequestBody Map<String, Object> request, @PathVariable("id") Long id){
//    return new ResponseEntity<>(customerService.updateCustomerDetails(id, request), HttpStatus.OK);
//}


//    @PatchMapping(value = "/update-customer/{id}")
//    public ResponseEntity<MessageResponse> updateCustomerDetails(@RequestBody JsonPatch request, @PathVariable("id") Long id){
//        return new ResponseEntity<>(customerService.updateUser(id, request), HttpStatus.OK);
//    }

//    @PatchMapping(value = "/update-customer/{userId}", consumes = {"application/json-patch+json"})
//    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, @RequestBody JsonPatch updatePatch){
//        try {
//            var response = customerService.updateCustomerDetails(userId, RegistrationRequest req);
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        }catch (Exception exception){
//            return ResponseEntity.badRequest().body(exception.getMessage());
//        }
//    }


    @GetMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request){
        return new ResponseEntity<>(customerService.login(request), HttpStatus.OK);
    }

    @GetMapping("/view-loan-status")
    public ResponseEntity<ApplicationStatusResponse> viewLoanStatus(@RequestBody ApplicationStatusRequest applicationStatusRequest){
        return new ResponseEntity<>(customerService.viewLoanApplicationStatus(applicationStatusRequest), HttpStatus.OK);
    }

    @GetMapping("/view-loan-agreement")
    public ResponseEntity<LoanAgreementResponse> loanAgreement(@RequestBody LoanAgreementRequest loanAgreementRequest){
        return new ResponseEntity<>(customerService.viewLoanAgreement(loanAgreementRequest), HttpStatus.OK);
    }


}
