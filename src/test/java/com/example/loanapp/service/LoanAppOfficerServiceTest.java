package com.example.loanapp.service;

import com.example.loanapp.dto.request.OfficerLoginRequest;
import com.example.loanapp.dto.response.OfficerLoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        loanOfficer.setPassword("MrJonesPassword");

        OfficerLoginResponse loginResponse = this.loanOfficerService.login(loanOfficer);

        assertEquals("Login Successful", loginResponse.getMessage());
        assertEquals("Mr Jones", loginResponse.getFullName());
    }

    @Test
    void loginTwoOfficers(){
        OfficerLoginRequest loanOfficer = new OfficerLoginRequest();
        loanOfficer.setFullName("Mr Dom");
        loanOfficer.setPassword("DomDom");

        OfficerLoginResponse loginResponse = this.loanOfficerService.login(loanOfficer);

        assertEquals("Login Successful", loginResponse.getMessage());
        assertEquals("Mr Dom", loginResponse.getFullName());
    }

    @Test
    void getLoanById(){

    }

    @Test
    void reviewLoanApplication() {
    }

    @Test
    void viewLoanApplications() {

    }

    @Test
    void viewLoanApplicationsDto() {
    }
}