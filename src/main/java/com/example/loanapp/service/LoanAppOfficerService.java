package com.example.loanapp.service;

import com.example.loanapp.data.model.Customer;
import com.example.loanapp.data.model.Loan;
import com.example.loanapp.data.model.LoanOfficer;
import com.example.loanapp.data.repositories.CustomerRepository;
import com.example.loanapp.data.repositories.LoanOfficerRepository;
import com.example.loanapp.data.repositories.LoanRepository;
import com.example.loanapp.dto.request.OfficerLoginRequest;
import com.example.loanapp.dto.request.UpdateLoanStatusRequest;
import com.example.loanapp.dto.response.LoanDto;
import com.example.loanapp.dto.response.OfficerLoginResponse;
import com.example.loanapp.dto.response.ViewLoanApplicationsDto;
import com.example.loanapp.exceptions.LoanNotFound;
import com.example.loanapp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    public LoanDto reviewLoanApplication(Long loanApplicationId) {
        Optional<LoanDto> foundLoan = this.loanRepository.findById(loanApplicationId)
                .stream()
                .map(this::loanDto)
                .findAny();
        return foundLoan.orElseThrow(()-> new LoanNotFound("Loan Not Found"));
    }

    private LoanDto loanDto(Loan loan) {
        LoanDto loanDto = new LoanDto();
        loanDto.setLoanApplicationStatus(loan.getLoanApplicationStatus());
        loanDto.setLoanAmount(loan.getLoanAmount());
        loanDto.setTenureInMonths(loan.getTenureInMonths());
        loanDto.setLoanApplicationStatus(loan.getLoanApplicationStatus());
        loanDto.setLoanOfficer(loan.getLoanOfficer());

        return loanDto;
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
    public void updateLoanStatus(UpdateLoanStatusRequest request) {
        Optional<Customer> foundCustomer = getCustomerByEmail(request.getUserEmail());

    }

    private Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findAll()
                .stream()
                .filter(customer -> findCustomerByEmail(customer, email))
                .findAny();
    }

    private boolean findCustomerByEmail(Customer customer, String email){
        return customer.getEmail().equals(email);
    }


    private ViewLoanApplicationsDto viewLoanApplicationsDto(Loan loan){
        ViewLoanApplicationsDto viewLoanApplicationsDto = new ViewLoanApplicationsDto();
        viewLoanApplicationsDto.setLoanAmount(loan.getLoanAmount());
        viewLoanApplicationsDto.setLoanPurpose(loan.getLoanPurpose());
        viewLoanApplicationsDto.setLoanApplicationStatus(loan.getLoanApplicationStatus());
        viewLoanApplicationsDto.setLoanOfficer(loan.getLoanOfficer());
        viewLoanApplicationsDto.setId(loan.getId());
//        viewLoanApplicationsDto.setLoanType(loan.getLoanType());
//        viewLoanApplicationsDto.setDateTime(loan.getLoanStartDate());
        viewLoanApplicationsDto.setTenureInMonths(loan.getTenureInMonths());

        return viewLoanApplicationsDto;
    }


}
