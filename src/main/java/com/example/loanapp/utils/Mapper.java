package com.example.loanapp.utils;


import com.example.loanapp.data.model.Customer;
import com.example.loanapp.data.model.Loan;
import com.example.loanapp.data.model.LoanOfficer;
import com.example.loanapp.dto.request.LoanApplicationRequest;
import com.example.loanapp.dto.request.OfficerLoginRequest;
import com.example.loanapp.dto.request.RegistrationRequest;
import com.example.loanapp.dto.response.LoanApplicationResponse;
import com.example.loanapp.dto.response.OfficerLoginResponse;
import com.example.loanapp.dto.response.RegistrationResponse;

import java.time.LocalDate;

public class Mapper {

    public static Customer map(RegistrationRequest registrationRequest){
        Customer newUser = new Customer();
        newUser.setAddress(registrationRequest.getAddress());
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setFirstName(registrationRequest.getFirstName());
        newUser.setLastName(registrationRequest.getLastName());
        newUser.setEmploymentStatus(registrationRequest.getEmploymentStatus());
        newUser.setPhoneNumber(registrationRequest.getPhoneNumber());
        newUser.setSex(registrationRequest.getSex());
        newUser.setAge(registrationRequest.getAge());
        newUser.setBasicMonthSalary(registrationRequest.getBasicMonthSalary());
        newUser.setEmploymentClassification(registrationRequest.getEmploymentClassification());
        newUser.setNameOfCurrentEmployer(registrationRequest.getNameOfCurrentEmployer());
        newUser.setPassword(registrationRequest.getPassword());
        return newUser;
    }

    public static RegistrationResponse map(Customer savedUser){
        RegistrationResponse response = new RegistrationResponse();

        if (savedUser!=null)response.setMessage("Registration Successful");

        return response;
    }

    public static Loan map(LoanApplicationRequest loanApplicationRequest){
        Loan newLoan = new Loan();
        newLoan.setAmountPerPaymentPeriod(loanApplicationRequest.getAmountPerPaymentPeriod());
        newLoan.setLoanAmount(loanApplicationRequest.getLoanAmount());
        newLoan.setLoanPurpose(loanApplicationRequest.getLoanPurpose());
        newLoan.setRepaymentPreference(loanApplicationRequest.getRepaymentPreference());
        newLoan.setTenureInMonths(loanApplicationRequest.getTenureInMonths());
//        newLoan.setLoanEndDate(LocalDate.ofEpochDay(newLoan.getLoanStartDate().getYear() + loanApplicationRequest.getTenureInMonths()));
//        LocalDate.ofEpochDay(startDate.getYear() + Integer.parseInt(loanTenure))
        return newLoan;
    }

    public static LoanApplicationResponse map(Loan savedLoan){
        LoanApplicationResponse response = new LoanApplicationResponse();
        response.setLoanAmount(savedLoan.getLoanAmount());
        response.setLoanPurpose(savedLoan.getLoanPurpose());
        response.setRepaymentPreference(savedLoan.getRepaymentPreference());
        response.setTenureInMonths(savedLoan.getTenureInMonths());
        response.setMessage("Application has been sent for review.");

        return response;
    }

    public static LoanOfficer map(OfficerLoginRequest officerLoginRequest){
        LoanOfficer loanOfficer = new LoanOfficer();

        loanOfficer.setFullName(officerLoginRequest.getFullName());
        return loanOfficer;
    }

    public static OfficerLoginResponse map(LoanOfficer loanOfficer){
        OfficerLoginResponse response = new OfficerLoginResponse();
        response.setFullName(loanOfficer.getFullName());
        response.setMessage("Login Successful \n Welcome " + response.getFullName());

        return response;
    }

}
