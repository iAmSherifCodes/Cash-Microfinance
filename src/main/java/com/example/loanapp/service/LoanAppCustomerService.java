package com.example.loanapp.service;


import com.example.loanapp.data.model.Customer;
import com.example.loanapp.data.model.Loan;
import com.example.loanapp.data.repositories.LoanRepository;
import com.example.loanapp.data.repositories.CustomerRepository;
import com.example.loanapp.dto.request.ApplicationStatusRequest;
import com.example.loanapp.dto.request.LoanApplicationRequest;
import com.example.loanapp.dto.request.LoginRequest;
import com.example.loanapp.dto.request.RegistrationRequest;
import com.example.loanapp.dto.response.ApplicationStatusResponse;
import com.example.loanapp.dto.response.LoanApplicationResponse;
import com.example.loanapp.dto.response.LoginResponse;
import com.example.loanapp.dto.response.RegistrationResponse;
import com.example.loanapp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LoanAppCustomerService implements CustomerService {
    private final CustomerRepository customerRepository;
    private final LoanRepository loanRepository;

    @Autowired
    public LoanAppCustomerService(CustomerRepository customerRepository, LoanRepository loanRepository) {
        this.customerRepository = customerRepository;
        this.loanRepository = loanRepository;
    }


    @Override
    public RegistrationResponse register(RegistrationRequest registrationRequest) {
        Customer newCustomer = Mapper.map(registrationRequest);
        Customer savedCustomer = this.customerRepository.save(newCustomer);
        return Mapper.map(savedCustomer);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<Customer> foundCustomer = this.customerRepository.findAll()
                .parallelStream()
                .filter((customer) -> isFound(loginRequest, customer))
                .findAny();

        LoginResponse response = new LoginResponse();
        if (foundCustomer.isPresent()) {
            response.setFoundCustomer(foundCustomer.get());
        } else {
            response.setErrorMessage("Customer not found");
        }
        return response;
    }


    private static boolean isFound(LoginRequest loginRequest, Customer customer) {
        return customer.getPassword().equals(loginRequest.getPassword())
                && customer.getPhoneNumber().equals(loginRequest.getPhoneNumber());
    }

    @Override
    public LoanApplicationResponse applyForLoan(LoanApplicationRequest loanApplicationRequest) {
        Loan newLoan = Mapper.map(loanApplicationRequest);
        Loan savedLoan = loanRepository.save(newLoan);
        return Mapper.map(savedLoan);
    }


    @Override
    public ApplicationStatusResponse viewLoanApplicationStatus(ApplicationStatusRequest applicationStatusRequest) {
        return null;
    }

    @Override
    public String viewLoanAgreement() {
        return null;
    }
}
