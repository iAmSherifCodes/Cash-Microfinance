package com.example.loanapp.service;

import com.example.loanapp.data.Enums.LoanStatus;
import com.example.loanapp.data.model.Customer;
import com.example.loanapp.data.model.Loan;
import com.example.loanapp.data.model.LoanOfficer;
import com.example.loanapp.data.repositories.CustomerRepository;
import com.example.loanapp.data.repositories.LoanOfficerRepository;
import com.example.loanapp.data.repositories.LoanRepository;
import com.example.loanapp.dto.request.OfficerLoginRequest;
import com.example.loanapp.dto.request.ReviewLoanRequest;
import com.example.loanapp.dto.request.UpdateLoanStatusRequest;
import com.example.loanapp.dto.response.ReviewLoanResponse;
import com.example.loanapp.dto.response.OfficerLoginResponse;
import com.example.loanapp.dto.response.UpdateLoanStatusResponse;
import com.example.loanapp.dto.response.ViewLoanApplicationsDto;
import com.example.loanapp.exceptions.LoanApplicationException;
import com.example.loanapp.utils.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Slf4j
public class LoanAppOfficerService implements LoanOfficerService{
    private final LoanOfficerRepository loanOfficerRepository;
    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public LoanAppOfficerService(LoanOfficerRepository loanOfficerRepository, LoanRepository loanRepository, CustomerRepository customerRepository) {
        this.loanOfficerRepository = loanOfficerRepository;
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;

    }

    @Override
    public OfficerLoginResponse login(OfficerLoginRequest officerLoginRequest) {
        LoanOfficer loanOfficer = Mapper.map(officerLoginRequest);
        LoanOfficer savedOfficer = this.loanOfficerRepository.save(loanOfficer);
        return Mapper.map(savedOfficer);
    }

    @Override
    public ReviewLoanResponse reviewLoanApplication(ReviewLoanRequest request) {
        Customer foundCustomer = this.customerRepository.findByEmail(request.getCustomerEmail()).orElseThrow(()->new LoanApplicationException("No Customer Found"));
        return getReviewLoanResponse(foundCustomer);
    }



    @Override
    public List<ViewLoanApplicationsDto> viewLoanApplications() {
        List<Loan> loans = this.loanRepository.findAll();
        return loans.stream().map((this::viewLoanApplicationsDto)).toList();

    }

    @Override
    public List<Customer> customers() {
        return this.customerRepository.findAll();
    }

    @Override
    public UpdateLoanStatusResponse updateLoanStatus(UpdateLoanStatusRequest request) {
        Long officerId = request.getOfficerId();
        Long loanId = request.getLoanId();
        LoanStatus loanStatus = request.getLoanStatus();

        Optional<Loan> foundLoan = this.loanRepository.findById(loanId);
        LoanOfficer foundOfficer = this.loanOfficerRepository
                .findById(officerId).orElseThrow(()->new LoanApplicationException("No Officer Found"));

        if (foundLoan.isPresent()){
            foundLoan.get().setLoanApplicationStatus(loanStatus);
            foundLoan.get().setLoanOfficer(foundOfficer);
            this.loanRepository.save(foundLoan.get());
        }

        UpdateLoanStatusResponse response = new UpdateLoanStatusResponse();
        if (foundLoan.isPresent()) response.setMessage("Successful");
        else response.setMessage("Loan Status Not Set");

        return response;

    }


    private ViewLoanApplicationsDto viewLoanApplicationsDto(Loan loan){
        ViewLoanApplicationsDto viewLoanApplicationsDto = new ViewLoanApplicationsDto();
        viewLoanApplicationsDto.setLoanAmount(loan.getLoanAmount());
        viewLoanApplicationsDto.setLoanPurpose(loan.getLoanPurpose());
        viewLoanApplicationsDto.setLoanApplicationStatus(loan.getLoanApplicationStatus());
        viewLoanApplicationsDto.setLoanOfficer(loan.getLoanOfficer());
        viewLoanApplicationsDto.setId(loan.getId());
        viewLoanApplicationsDto.setTenureInMonths(loan.getTenureInMonths());

        return viewLoanApplicationsDto;
    }

    private static ReviewLoanResponse getReviewLoanResponse(Customer foundCustomer) {
        ReviewLoanResponse response = new ReviewLoanResponse();
        response.setCustomerFirstName(foundCustomer.getFirstName());
        response.setCustomerLastName(foundCustomer.getLastName());
        response.setCustomerAddress(foundCustomer.getAddress());
        response.setCustomerPhoneNumber(foundCustomer.getPhoneNumber());
        response.setCustomerEmail(foundCustomer.getEmail());
        response.setCustomerSex(foundCustomer.getSex());
        response.setCustomerAge(foundCustomer.getAge());
        response.setCustomerEmploymentStatus(foundCustomer.getEmploymentStatus());
        response.setCustomerEmploymentClassification(foundCustomer.getEmploymentClassification());
        response.setCustomerNameOfCurrentEmployer(foundCustomer.getNameOfCurrentEmployer());
        response.setCustomerBasicMonthSalary(foundCustomer.getBasicMonthSalary());
        response.setCustomerLoanAmount(foundCustomer.getLoan().getLoanAmount());
        response.setCustomerTenureInMonths(foundCustomer.getLoan().getTenureInMonths());
        response.setLoanApplicationStatus(foundCustomer.getLoan().getLoanApplicationStatus());
        response.setLoanOfficer(foundCustomer.getLoan().getLoanOfficer());
        return response;
    }


}
