package com.banking.loans.service.impl;

import com.banking.loans.contants.LoansConstants;
import com.banking.loans.dto.LoansDto;
import com.banking.loans.entity.LoansEntity;
import com.banking.loans.exception.LoanAlreadyExistsException;
import com.banking.loans.exception.ResourceNotFoundException;
import com.banking.loans.repository.LoansRepository;
import com.banking.loans.service.ILoansService;
import com.banking.loans.transformer.LoansTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoansService implements ILoansService {

    @Autowired
    private LoansRepository loansRepository;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<LoansEntity> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);

        if(optionalLoans.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with the given mobile number "+mobileNumber);
        }

        loansRepository.save(createNewLoan(mobileNumber));
    }

    /**
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        LoansEntity loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber));
        return LoansTransformer.toDto(loans);
    }

    /**
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        LoansEntity loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Loan Number", loansDto.getLoanNumber())
        );
        LoansTransformer.toEntity(loansDto, loans);
        loansRepository.save(loans);
        return true;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        LoansEntity loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber)
        );
        loansRepository.delete(loans);
        return true;
    }

    /**
     *
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */
    private LoansEntity createNewLoan(String mobileNumber) {
        long loanNumber = 100000000000L + new Random().nextInt(900000000);
        return LoansEntity.builder().loanNumber(Long.toString(loanNumber)).mobileNumber(mobileNumber).loanType(LoansConstants.HOME_LOAN).totalLoan(LoansConstants.NEW_LOAN_LIMIT).amountPaid(0).outstandingAmount(LoansConstants.NEW_LOAN_LIMIT).build();
    }
}
