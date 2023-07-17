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
        newUser.setBasicMonthSalary(registrationRequest.getBasicMonthSalary());
        newUser.setEmploymentClassification(registrationRequest.getEmploymentClassification());
        newUser.setNameOfCurrentEmployer(registrationRequest.getNameOfCurrentEmployer());
        newUser.setPassword(registrationRequest.getPassword());
        return newUser;
    }

    public static RegistrationResponse map(Customer savedUser){
        RegistrationResponse response = new RegistrationResponse();
        response.setAddress(savedUser.getAddress());
        response.setEmail(savedUser.getEmail());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setEmploymentStatus(savedUser.getEmploymentStatus());
        response.setPhoneNumber(savedUser.getPhoneNumber());
        response.setSex(savedUser.getSex());
        response.setBasicMonthSalary(savedUser.getBasicMonthSalary());
        response.setEmploymentClassification(savedUser.getEmploymentClassification());
        response.setNameOfCurrentEmployer(savedUser.getNameOfCurrentEmployer());
        response.setMessage("Registration Successful");
        return response;
    }

    public static Loan map(LoanApplicationRequest loanApplicationRequest){
        Loan newLoan = new Loan();
        newLoan.setLoanAmount(loanApplicationRequest.getLoanAmount());
        newLoan.setLoanType(loanApplicationRequest.getLoanType());
        newLoan.setLoanPurpose(loanApplicationRequest.getLoanPurpose());
        newLoan.setTenureInMonths(loanApplicationRequest.getTenureInWeeks());
        return newLoan;
    }

    public static LoanApplicationResponse map(Loan savedLoan){
        LoanApplicationResponse response = new LoanApplicationResponse();
        response.setLoanAmount(savedLoan.getLoanAmount());
        response.setLoanPurpose(savedLoan.getLoanPurpose());
        response.setLoanType(savedLoan.getLoanType());
        response.setTenureInMonths(savedLoan.getTenureInMonths());

        return response;
    }

    public static LoanOfficer map(OfficerLoginRequest officerLoginRequest){
        LoanOfficer loanOfficer = new LoanOfficer();

        loanOfficer.setFullName(officerLoginRequest.getFullName());
        loanOfficer.setPassword(officerLoginRequest.getPassword());
        return loanOfficer;
    }

    public static OfficerLoginResponse map(LoanOfficer loanOfficer){
        OfficerLoginResponse response = new OfficerLoginResponse();
        response.setFullName(loanOfficer.getFullName());
        response.setMessage("Login Successful");

        return response;
    }

}
