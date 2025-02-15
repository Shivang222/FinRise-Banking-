package com.banking.accounts.transformer;

import com.banking.accounts.dto.AccountsDto;
import com.banking.accounts.entity.AccountsEntity;

public class AccountsTransformer {

    public static AccountsDto mapToAccountsDto(AccountsEntity accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static AccountsEntity mapToAccounts(AccountsDto accountsDto, AccountsEntity accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
