package com.example.loanapp.data.repositories;

import com.example.loanapp.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
//    Optional<Customer> findByPhoneNumber(String phoneNumber);
//    Optional<Customer> findByEmail(String email);
//    Optional<Customer> findByPassword(String password);
}
