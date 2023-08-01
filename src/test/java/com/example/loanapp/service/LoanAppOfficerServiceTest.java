package com.example.loanapp.service;

import com.example.loanapp.data.Enums.LoanStatus;
import com.example.loanapp.dto.request.OfficerLoginRequest;
import com.example.loanapp.dto.request.ReviewLoanRequest;
import com.example.loanapp.dto.request.UpdateLoanStatusRequest;
import com.example.loanapp.dto.response.ReviewLoanResponse;
import com.example.loanapp.dto.response.OfficerLoginResponse;
import com.example.loanapp.dto.response.UpdateLoanStatusResponse;

import com.example.loanapp.exceptions.LoanApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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

//    @Test
//    void getNumberOfCustomers(){
//        assertEquals(5, this.loanOfficerService.customers().size());
//    }

    @Test
    void reviewLoanApplication() {
        ReviewLoanResponse reviewLoanResponse = this.loanOfficerService.reviewLoanApplication(new ReviewLoanRequest("gbolahunBams23@gmail.com"));
        assertNotNull(reviewLoanResponse);
        assertNotNull(reviewLoanResponse.getCustomerAddress());
        assertNotNull(reviewLoanResponse.getCustomerLoanAmount());
    }

    @Test
    void viewLoanApplications() {
        assertEquals(2, (long) this.loanOfficerService.viewLoanApplications().size());
    }

    @Test
    void updateLoanApplicationsStatus() {
        LoanStatus status = LoanStatus.CLOSED;
        Long loanId = 1L;
        Long officerId = 1L;

        UpdateLoanStatusResponse response = this.loanOfficerService.updateLoanStatus(new UpdateLoanStatusRequest(officerId, loanId, status));
        assertNotNull(response);
        assertEquals("Successful", response.getMessage());
    }

    @Test
    void updateLoanApplicationsStatusIsInvalid() {
        LoanStatus status = LoanStatus.IN_PROGRESS;
        Long loanId = 4L;
        Long officerId = 4L;

        assertThrows(LoanApplicationException.class, ()->this.loanOfficerService.updateLoanStatus(new UpdateLoanStatusRequest(officerId, loanId, status)));

    }
}