package com.example.loanapp.service;

import com.example.loanapp.data.model.Customer;
import com.example.loanapp.data.model.Loan;
import com.example.loanapp.data.repositories.CustomerRepository;
import com.example.loanapp.data.repositories.LoanRepository;
import com.example.loanapp.dto.request.LoanApplicationRequest;
import com.example.loanapp.dto.request.LoginRequest;
import com.example.loanapp.dto.request.RegistrationRequest;
//import com.example.loanapp.dto.request.ViewApplicationStatusRequest;
//import com.example.loanapp.dto.request.ViewApplicationStatusRequest;
import com.example.loanapp.dto.response.LoanApplicationResponse;
import com.example.loanapp.dto.response.LoginResponse;
import com.example.loanapp.dto.response.RegistrationResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static com.example.loanapp.data.Enums.EmploymentClassification.SENIOR_STAFF;
import static com.example.loanapp.data.Enums.EmploymentStatus.CONTRACT;
import static com.example.loanapp.data.Enums.LoanType.NO_WAHALA;
import static com.example.loanapp.data.Enums.Sex.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class LoanAppCustomerServiceTest {

    private final LoanRepository loanRepository;
    private final CustomerService customerService;

    private final CustomerRepository customerRepository;
    @Autowired
    public LoanAppCustomerServiceTest(CustomerService customerService, LoanRepository loanRepository, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
    }



    @AfterEach
    void deleteDataCreated(){
        Optional<Loan> lastLoan = this.loanRepository.findAll()
                .stream()
                .filter((loan)->loan.getId() == loanRepository.count()-1)
                .findAny();
        lastLoan.ifPresent((loanRepository::delete));
    }


    @Test
    public void register() {
        LoanApplicationRequest request = new LoanApplicationRequest();
        request.setLoanAmount(BigDecimal.valueOf(909090));
        request.setLoanType(NO_WAHALA);
        request.setLoanPurpose("House Rent");
        request.setTenureInWeeks(34);

        this.customerService.applyForLoan(request);

        RegistrationRequest user = new RegistrationRequest();
        user.setAddress("5 St. Jones");
        user.setEmail("johndoe@gmail.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setSex(MALE);
        user.setPhoneNumber("0909090");
        user.setPassword("1234pass");
        user.setEmploymentClassification(SENIOR_STAFF);
        user.setEmploymentStatus(CONTRACT);
        user.setNameOfCurrentEmployer("Semicolon");
        user.setBasicMonthSalary(BigDecimal.valueOf(20000));

        Loan foundLoan = loanRepository.findAll().get(0);


        RegistrationResponse response =  customerService.register(user);

        Customer foundCustomer = customerRepository.findAll().get(0);
        foundCustomer.setLoan(foundLoan);

        customerRepository.save(foundCustomer);



        assertEquals(user.getFirstName(), response.getFirstName());
        assertEquals("Registration Successful", response.getMessage());
    }

    @Test
    public void login() {
        LoginRequest request = new LoginRequest();
        request.setPhoneNumber("0909090");
        request.setPassword("1234pass");

        LoginResponse response = customerService.login(request);
        assertEquals("Semicolon", response.getFoundCustomer().getNameOfCurrentEmployer());
        assertEquals("Doe", response.getFoundCustomer().getLastName());
    }

    @Test
    public void applyForLoan() {

        LoanApplicationRequest request = new LoanApplicationRequest();
        request.setLoanAmount(BigDecimal.valueOf(909090));
        request.setLoanType(NO_WAHALA);
        request.setLoanPurpose("House Rent");
        request.setTenureInWeeks(34);

        LoanApplicationResponse response = this.customerService.applyForLoan(request);

        assertEquals("House Rent", response.getLoanPurpose());
        assertEquals(34, response.getTenureInMonths());
    }

    @Test
    public void viewLoanApplicationStatus() {
        LoanApplicationRequest request = new LoanApplicationRequest();
        request.setLoanAmount(BigDecimal.valueOf(909090));
        request.setLoanType(NO_WAHALA);
        request.setLoanPurpose("House Rent");
        request.setTenureInWeeks(34);

        LoanApplicationResponse response = this.customerService.applyForLoan(request);

//        ViewApplicationStatusRequest viewRequest = new ViewApplicationStatusRequest();
//        viewRequest.setRequest(true);
//        assertEquals();
    }

    @Test
    public void viewLoanAgreement() {
    }

    @Test
    void numberOfRegisteredCustomers(){
        assertEquals(1, this.loanRepository.count());
    }
}