package com.example.loanapp.service;

import com.example.loanapp.data.repositories.CustomerRepository;
import com.example.loanapp.data.repositories.LoanRepository;
import com.example.loanapp.dto.request.*;
import com.example.loanapp.dto.response.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.example.loanapp.data.Enums.EmploymentClassification.*;
import static com.example.loanapp.data.Enums.EmploymentStatus.CONTRACT;
import static com.example.loanapp.data.Enums.EmploymentStatus.FULL_EMPLOYMENT;
import static com.example.loanapp.data.Enums.RepaymentPreferences.PAYPAL;
import static com.example.loanapp.data.Enums.Sex.FEMALE;
import static com.example.loanapp.data.Enums.Sex.MALE;
import static org.junit.jupiter.api.Assertions.*;

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



//    @AfterEach
//    void deleteDataCreated(){
//        Optional<Loan> lastLoan = this.loanRepository.findAll()
//                .stream()
//                .filter((loan)->loan.getId() == loanRepository.count()-1)
//                .findAny();
//        lastLoan.ifPresent((loanRepository::delete));
//    }


    @Test
    public void register() {
        RegistrationRequest user = new RegistrationRequest();
        user.setAddress("5A Liverpool, UK"); //4
        user.setEmail("gbolahunBams23@gmail.com"); //6
        user.setFirstName("Gbolahun"); //1
        user.setLastName("Bams"); //2
        user.setSex(MALE); //7
        user.setAge(42); //8
        user.setPhoneNumber("0450900"); //5
        user.setPassword("bamsNiSeh");//3
        user.setEmploymentClassification(SENIOR_STAFF); //10
        user.setEmploymentStatus(FULL_EMPLOYMENT); //9
        user.setNameOfCurrentEmployer("Liverpool FC. Security Dept."); //11
        user.setBasicMonthSalary(BigDecimal.valueOf(500000000)); //12



        MessageResponse response =  customerService.register(user);




        assertNotNull(user);
        assertEquals("Registration Successful", response.getMessage());
    }

    @Test
    public void registerSecondCustomer() {
        RegistrationRequest user = new RegistrationRequest();
        user.setAddress("No 4, Adewale street, Lekki Gardens"); //4
        user.setEmail("adewales23@gmail.com"); //6
        user.setFirstName("Adewale"); //1
        user.setLastName("Bobo"); //2
        user.setSex(MALE); //7
        user.setAge(32); //8
        user.setPhoneNumber("09099000000"); //5
        user.setPassword("pass1");//3
        user.setEmploymentClassification(JUNIOR_STAFF); //10
        user.setEmploymentStatus(FULL_EMPLOYMENT); //9
        user.setNameOfCurrentEmployer("New Jersey"); //11
        user.setBasicMonthSalary(BigDecimal.valueOf(4500000)); //12



        MessageResponse response =  customerService.register(user);




        assertNotNull(user);
        assertEquals("Registration Successful", response.getMessage());
    }

    @Test
    public void registerThirdCustomer() {
        RegistrationRequest user = new RegistrationRequest();
        user.setAddress("34, Herbert"); //4
        user.setEmail("jamesAyo23@gmail.com"); //6
        user.setFirstName("James"); //1
        user.setLastName("Ayomide"); //2
        user.setSex(FEMALE); //7
        user.setAge(23); //8
        user.setPhoneNumber("0901000000"); //5
        user.setPassword("pass2");//3
        user.setEmploymentClassification(JUNIOR_MANAGEMENT); //10
        user.setEmploymentStatus(CONTRACT); //9
        user.setNameOfCurrentEmployer("Arsenal FC."); //11
        user.setBasicMonthSalary(BigDecimal.valueOf(430000)); //12



        MessageResponse response =  customerService.register(user);




        assertNotNull(user);
        assertEquals("Registration Successful", response.getMessage());
    }

    @Test
    public void login() {
        LoginRequest request = new LoginRequest();
        request.setEmail("07037000000");
        request.setPassword("unHaCkAbLe");

        LoginResponse response = customerService.login(request);
        assertNull(response.getErrorMessage());
        assertEquals("Dangote Cement LTD.", response.getFoundCustomer().getNameOfCurrentEmployer());
        assertEquals("Adebamike", response.getFoundCustomer().getLastName());
    }

    @Test
    void invalidLogin(){
        LoginRequest request = new LoginRequest();
        request.setEmail("09045490323");
        request.setPassword("hackMe");

        LoginResponse response = customerService.login(request);
        assertEquals("Customer not found", response.getErrorMessage());
    }

    @Test
    public void applyForLoan() {

        LoanApplicationRequest request = new LoanApplicationRequest();
        request.setEmail("gbolahunBams23@gmail.com");
        request.setLoanAmount(BigDecimal.valueOf(455000000));
        request.setLoanPurpose("Children Investment");
        request.setTenureInMonths(10);
        request.setAmountPerPaymentPeriod(BigDecimal.valueOf(100000));
        request.setRepaymentPreference(PAYPAL);


        LoanApplicationResponse response = this.customerService.applyForLoan(request);

        assertEquals("Children Investment", response.getLoanPurpose());
        assertNotNull(response);
        assertEquals(10, response.getTenureInMonths());
    }

    @Test
    public void viewLoanApplicationStatus() {
        ApplicationStatusRequest applicationStatusRequest = new ApplicationStatusRequest();
        applicationStatusRequest.setEmail("ades@gmail.com");



        ApplicationStatusResponse applicationStatusResponse = customerService.viewLoanApplicationStatus(applicationStatusRequest);

        assertNotNull(applicationStatusResponse);
        assertEquals("IN_PROGRESS", applicationStatusResponse.getApplicationStatus());

//
    }

    @Test
    public void viewLoanAgreement() {
        LoanAgreementRequest loanAgreementRequest = new LoanAgreementRequest();
        loanAgreementRequest.setEmail("ades@gmail.com");

        LoanAgreementResponse response = customerService.viewLoanAgreement(loanAgreementRequest);

        assertNotNull(response);
        assertNotNull(response.getAddress());
        assertNotNull(response.toString());
    }

//    @Test
//    void numberOfRegisteredCustomers(){
//        assertEquals(1, this.loanRepository.count());
//    }
}