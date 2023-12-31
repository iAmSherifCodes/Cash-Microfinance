package com.example.loanapp.data.repositories;

import com.example.loanapp.data.model.Customer;
import com.example.loanapp.data.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
//    Optional<Customer> findByPhoneNumber(String phoneNumber);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByLoan(Loan loan);
    Optional<Customer> findByPassword(String password);
}
