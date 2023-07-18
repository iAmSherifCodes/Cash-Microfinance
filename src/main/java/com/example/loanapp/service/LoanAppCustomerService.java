package com.example.loanapp.service;


import com.example.loanapp.data.model.Customer;
import com.example.loanapp.data.model.Loan;
import com.example.loanapp.data.repositories.LoanRepository;
import com.example.loanapp.data.repositories.CustomerRepository;
import com.example.loanapp.dto.request.*;
import com.example.loanapp.dto.response.*;
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
                .filter((customer) -> isUserFound(loginRequest, customer))
                .findAny();

        LoginResponse response = new LoginResponse();
        if (foundCustomer.isPresent()) {
            response.setFoundCustomer(foundCustomer.get());
        } else {
            response.setErrorMessage("Customer not found");
        }
        return response;
    }


    private static boolean isUserFound(LoginRequest loginRequest, Customer customer) {
        return customer.getPassword().equals(loginRequest.getPassword())
                && customer.getPhoneNumber().equals(loginRequest.getPhoneNumber());
    }

    private boolean findCustomerByEmail(Customer customer, String email){
        return customer.getEmail().equals(email);
    }

    @Override
    public LoanApplicationResponse applyForLoan(LoanApplicationRequest loanApplicationRequest) {
        Optional<Customer> foundCustomer = getCustomerByEmail(loanApplicationRequest.getEmail());


        Loan newLoan = Mapper.map(loanApplicationRequest);
        Loan savedLoan = loanRepository.save(newLoan);
        foundCustomer.ifPresent(customer -> {
            customer.setLoan(savedLoan);
            customerRepository.save(foundCustomer.get());
        });

        return Mapper.map(savedLoan);
    }

    private Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findAll()
                .stream()
                .filter(customer -> findCustomerByEmail(customer, email))
                .findAny();
    }


    @Override
    public ApplicationStatusResponse viewLoanApplicationStatus(ApplicationStatusRequest applicationStatusRequest) {
        ApplicationStatusResponse applicationStatusResponse = new ApplicationStatusResponse();
        Optional<Customer> foundCustomer = getCustomerByEmail(applicationStatusRequest.getEmail());

        if(foundCustomer.isPresent()) {
            applicationStatusResponse.setApplicationStatus(String.valueOf(foundCustomer.get().getLoan().getLoanApplicationStatus()));
        }else applicationStatusResponse.setNotFoundMessage("Loan Application Not Found");

        return applicationStatusResponse;
    }

    @Override
    public LoanAgreementResponse viewLoanAgreement(LoanAgreementRequest loanAgreementRequest) {
        Optional<Customer> foundCustomer = getCustomerByEmail(loanAgreementRequest.getEmail());
//        if (foundCustomer.isPresent() && foundCustomer.get().getLoan()==null){
//            return ;}
//        LocalDateTime dateTime = LocalDateTime.now();

        LoanAgreementResponse loanAgreementResponse = new LoanAgreementResponse();
        if(foundCustomer.isPresent() && foundCustomer.get().getLoan()!=null) {
            loanAgreementResponse.setFirstName(foundCustomer.get().getFirstName());
            loanAgreementResponse.setLastName(foundCustomer.get().getLastName());
            loanAgreementResponse.setEmail(foundCustomer.get().getEmail());
            loanAgreementResponse.setPhoneNumber(foundCustomer.get().getPhoneNumber());
            loanAgreementResponse.setAddress(foundCustomer.get().getAddress());
            loanAgreementResponse.setLoanAmount(foundCustomer.get().getLoan().getLoanAmount());
//            loanAgreementResponse.setStartDate(foundCustomer.get().getLoan().getLoanStartDate());
//            loanAgreementResponse.setEndDate(foundCustomer.get().getLoan().getLoanEndDate());
            loanAgreementResponse.setRepaymentPreference(String.valueOf(foundCustomer.get().getLoan().getRepaymentPreference()));
            loanAgreementResponse.setLoanTenure(String.valueOf(foundCustomer.get().getLoan().getTenureInMonths()));
            loanAgreementResponse.setAmountPerPaymentPeriod(foundCustomer.get().getLoan().getAmountPerPaymentPeriod());
//            loanAgreementResponse.setPaymentMethod();

        }else loanAgreementResponse.setErrorMessage("Not Found");
        return loanAgreementResponse;
    }
}
