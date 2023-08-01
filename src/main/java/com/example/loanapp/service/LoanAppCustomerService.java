package com.example.loanapp.service;


import com.example.loanapp.data.model.Customer;
import com.example.loanapp.data.model.Loan;
import com.example.loanapp.data.repositories.CustomerRepository;
import com.example.loanapp.data.repositories.LoanRepository;
import com.example.loanapp.dto.request.*;
import com.example.loanapp.dto.response.*;
import com.example.loanapp.exceptions.LoanApplicationException;
import com.example.loanapp.utils.Mapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
//@RequiredArgsConstructor
public class LoanAppCustomerService implements CustomerService {
    private final CustomerRepository customerRepository;
    private final LoanRepository loanRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LoanAppCustomerService(CustomerRepository customerRepository, LoanRepository loanRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.loanRepository = loanRepository;
    }


    @Override
    public MessageResponse register(RegistrationRequest registrationRequest) {
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

    @Override
    public MessageResponse updateCustomerDetails(Long id, RegistrationRequest request){
        Customer foundCustomer = customerRepository.findById(id).orElseThrow(()-> new LoanApplicationException("Could not Update user profile"));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(request, foundCustomer);
        this.customerRepository.save(foundCustomer);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Update Successful");

        return messageResponse;
    }




//    @Override
//    public MessageResponse updateCustomerDetails(Long id , RegistrationRequest request) {
//        Customer foundCustomer = customerRepository.findById(id)
//                                                   .orElseThrow(() -> new LoanApplicationException("No Customer Found"));
//        Customer newCustomer = Mapper.map(foundCustomer, request);
//        this.customerRepository.save(newCustomer);
//        MessageResponse response = new MessageResponse();
//        response.setMessage("Successful");
//        return response;
//    }

//    @Override
//    public MessageResponse updateUser(Long userId, JsonPatch updatePayload) {
//
//        ObjectMapper mapper = new ObjectMapper();
//        Customer foundUser = customerRepository.findById(userId).orElseThrow(()-> new LoanApplicationException("No Customer Found"));
//        //User Object to node
//        JsonNode node = mapper.convertValue(foundUser, JsonNode.class);
//        try {
//            //apply patch
//            JsonNode updatedNode = updatePayload.apply(node);
//            //node to Passenger Object
//            var updatedUser = mapper.convertValue(updatedNode, Customer.class);
//            customerRepository.save(updatedUser);
//            MessageResponse response = new MessageResponse();
//            response.setMessage("Update Successful");
//            return response;
//
//        } catch (JsonPatchException e) {
////            log.error(e.getMessage());
//            throw new RuntimeException();
//        }
//    }


    private static boolean isUserFound(LoginRequest loginRequest, Customer customer) {
        return customer.getPassword().equals(loginRequest.getPassword())
                && customer.getEmail().equals(loginRequest.getEmail());
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

        LoanAgreementResponse loanAgreementResponse = new LoanAgreementResponse();
        if(foundCustomer.isPresent() && foundCustomer.get().getLoan()!=null) {
            loanAgreementResponse.setFirstName(foundCustomer.get().getFirstName());
            loanAgreementResponse.setLastName(foundCustomer.get().getLastName());
            loanAgreementResponse.setEmail(foundCustomer.get().getEmail());
            loanAgreementResponse.setPhoneNumber(foundCustomer.get().getPhoneNumber());
            loanAgreementResponse.setAddress(foundCustomer.get().getAddress());
            loanAgreementResponse.setLoanAmount(foundCustomer.get().getLoan().getLoanAmount());
            loanAgreementResponse.setRepaymentPreference(String.valueOf(foundCustomer.get().getLoan().getRepaymentPreference()));
            loanAgreementResponse.setLoanTenure(String.valueOf(foundCustomer.get().getLoan().getTenureInMonths()));
            loanAgreementResponse.setAmountPerPaymentPeriod(foundCustomer.get().getLoan().getAmountPerPaymentPeriod());

        }else loanAgreementResponse.setErrorMessage("Not Found");
        return loanAgreementResponse;
    }
}
