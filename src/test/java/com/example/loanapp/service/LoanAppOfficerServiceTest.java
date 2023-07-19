package com.example.loanapp.service;

import com.example.loanapp.dto.request.OfficerLoginRequest;
import com.example.loanapp.dto.response.LoanDto;
import com.example.loanapp.dto.response.OfficerLoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LoanAppOfficerServiceTest {

    private final LoanOfficerService loanOfficerService;


    @Autowired
    public LoanAppOfficerServiceTest(LoanOfficerService loanOfficerService) {
        this.loanOfficerService = loanOfficerService;
    }

    @BeforeEach
    void setUp() {

    }



    @Test
    void login() {
        OfficerLoginRequest loanOfficer = new OfficerLoginRequest();
        loanOfficer.setFullName("Mr Jones");

        OfficerLoginResponse loginResponse = this.loanOfficerService.login(loanOfficer);

        assertNotNull(loginResponse.getMessage());
        assertEquals("Mr Jones", loginResponse.getFullName());
    }

    @Test
    void loginTwoOfficers(){
        OfficerLoginRequest loanOfficer = new OfficerLoginRequest();
        loanOfficer.setFullName("Mr Dom");

        OfficerLoginResponse loginResponse = this.loanOfficerService.login(loanOfficer);

//        assertEquals("Login Successful", loginResponse.getMessage());
        assertNotNull(loginResponse.getMessage());
        assertEquals("Mr Dom", loginResponse.getFullName());
    }

    @Test
    void getNumberOfCustomers(){
        assertEquals(5, this.loanOfficerService.customers().size());
    }

    @Test
    void reviewLoanApplication() {
        LoanDto loanDto = this.loanOfficerService.reviewLoanApplication(3L);
        assertNotNull(loanDto);
        assertNotNull(loanDto.getCustomerAddress());
        assertNotNull(loanDto.getCustomerLoanAmount());
    }

    @Test
    void viewLoanApplications() {
        assertEquals(2, (long) this.loanOfficerService.viewLoanApplications().size());
    }

    @Test
    void viewLoanApplicationsDto() {
    }
}