package com.banking.loans.transformer;

import com.banking.loans.dto.LoansDto;
import com.banking.loans.entity.LoansEntity;

public class LoansTransformer {

    public static LoansDto toDto(LoansEntity pLoansEntity) {
        if(pLoansEntity == null) {
            return null;
        }
        return LoansDto.builder().loanNumber(pLoansEntity.getLoanNumber()).loanType(pLoansEntity.getLoanType()).mobileNumber(pLoansEntity.getMobileNumber()).totalLoan(pLoansEntity.getTotalLoan()).amountPaid(pLoansEntity.getAmountPaid()).outstandingAmount(pLoansEntity.getOutstandingAmount()).build();
    }

    public static LoansEntity toEntity(LoansDto pLoansDto, LoansEntity pLoans) {
        if(pLoansDto == null) {
            return null;
        }
        LoansEntity loans = pLoans;
        if(pLoans == null) {
            loans = new LoansEntity();
        }
        loans.setLoanNumber(pLoansDto.getLoanNumber());
        loans.setLoanType(pLoansDto.getLoanType());
        loans.setMobileNumber(pLoansDto.getMobileNumber());
        loans.setTotalLoan(pLoansDto.getTotalLoan());
        loans.setAmountPaid(pLoansDto.getAmountPaid());
        loans.setOutstandingAmount(pLoansDto.getOutstandingAmount());
        return loans;
    }
}
