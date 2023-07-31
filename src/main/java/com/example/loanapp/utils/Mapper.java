package com.example.loanapp.utils;


import com.example.loanapp.data.model.Customer;
import com.example.loanapp.data.model.Loan;
import com.example.loanapp.data.model.LoanOfficer;
import com.example.loanapp.dto.request.LoanApplicationRequest;
import com.example.loanapp.dto.request.OfficerLoginRequest;
import com.example.loanapp.dto.request.RegistrationRequest;
import com.example.loanapp.dto.response.*;

import java.time.LocalDate;
import java.util.Optional;

public class Mapper {

    public static Customer map(RegistrationRequest registrationRequest){
        Customer newUser = new Customer();
        return mapCustomer(newUser, registrationRequest);
    }

    public static Customer map(Customer customer ,RegistrationRequest registrationRequest){
        return mapCustomer(customer, registrationRequest);
    }

    private static Customer mapCustomer(Customer customer, RegistrationRequest registrationRequest) {
        String getAddress;
        if (registrationRequest.getAddress() != null) getAddress = registrationRequest.getAddress();


//        customer.setAddress(getAddress.);
        customer.setEmail(registrationRequest.getEmail());
        customer.setFirstName(registrationRequest.getFirstName());
        customer.setLastName(registrationRequest.getLastName());
        customer.setEmploymentStatus(registrationRequest.getEmploymentStatus());
        customer.setPhoneNumber(registrationRequest.getPhoneNumber());
        customer.setSex(registrationRequest.getSex());
        customer.setAge(registrationRequest.getAge());
        customer.setBasicMonthSalary(registrationRequest.getBasicMonthSalary());
        customer.setEmploymentClassification(registrationRequest.getEmploymentClassification());
        customer.setNameOfCurrentEmployer(registrationRequest.getNameOfCurrentEmployer());
        customer.setPassword(registrationRequest.getPassword());
        return customer;
    }

    public static MessageResponse map(Customer savedUser){
        MessageResponse response = new MessageResponse();
        if (savedUser!=null)response.setMessage("Registration Successful");
        return response;
    }

    public static Loan map(LoanApplicationRequest loanApplicationRequest){
        Loan newLoan = new Loan();
        newLoan.setLoanStartDate(LocalDate.now());
        newLoan.setAmountPerPaymentPeriod(loanApplicationRequest.getAmountPerPaymentPeriod());
        newLoan.setLoanAmount(loanApplicationRequest.getLoanAmount());
        newLoan.setLoanPurpose(loanApplicationRequest.getLoanPurpose());
        newLoan.setRepaymentPreference(loanApplicationRequest.getRepaymentPreference());
        newLoan.setTenureInMonths(loanApplicationRequest.getTenureInMonths());
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

    public static void loanDtoMapper(Optional<Customer> foundCustomer, Optional<Loan> foundLoan, ReviewLoanResponse reviewLoanResponse) {
        if (foundCustomer.isPresent() && foundLoan.isPresent()){
        reviewLoanResponse.setCustomerFirstName(foundCustomer.get().getFirstName());
        reviewLoanResponse.setCustomerLastName(foundCustomer.get().getLastName());
        reviewLoanResponse.setCustomerAddress(foundCustomer.get().getAddress());
        reviewLoanResponse.setCustomerPhoneNumber(foundCustomer.get().getPhoneNumber());
        reviewLoanResponse.setCustomerEmail(foundCustomer.get().getEmail());
        reviewLoanResponse.setCustomerSex(foundCustomer.get().getSex());
        reviewLoanResponse.setCustomerAge(foundCustomer.get().getAge());
        reviewLoanResponse.setCustomerEmploymentStatus(foundCustomer.get().getEmploymentStatus());
        reviewLoanResponse.setCustomerEmploymentClassification(foundCustomer.get().getEmploymentClassification());
        reviewLoanResponse.setCustomerNameOfCurrentEmployer(foundCustomer.get().getNameOfCurrentEmployer());
        reviewLoanResponse.setCustomerBasicMonthSalary(foundCustomer.get().getBasicMonthSalary());

        reviewLoanResponse.setLoanApplicationStatus(foundLoan.get().getLoanApplicationStatus());
        reviewLoanResponse.setCustomerLoanAmount(foundLoan.get().getLoanAmount());
        reviewLoanResponse.setCustomerTenureInMonths(foundLoan.get().getTenureInMonths());
        reviewLoanResponse.setLoanApplicationStatus(foundLoan.get().getLoanApplicationStatus());
        reviewLoanResponse.setLoanOfficer(foundLoan.get().getLoanOfficer());
        }
    }





}
