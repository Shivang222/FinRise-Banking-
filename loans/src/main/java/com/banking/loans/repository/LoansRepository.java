package com.banking.loans.repository;

import com.banking.loans.entity.LoansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoansRepository extends JpaRepository<LoansEntity, Long> {

    Optional<LoansEntity> findByMobileNumber(String mobileNumber);

    Optional<LoansEntity> findByLoanNumber(String loanNumber);
}
