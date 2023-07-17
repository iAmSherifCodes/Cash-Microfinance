package com.example.loanapp.data.repositories;

import com.example.loanapp.data.model.LoanOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface LoanOfficerRepository extends JpaRepository<LoanOfficer, Long> {
}
