package io.github.thepragmaticsquad.fs.service;

import io.github.thepragmaticsquad.fs.dto.account.AccountAbstractedDto;
import io.github.thepragmaticsquad.fs.dto.account.AccountDetailedDto;
import io.github.thepragmaticsquad.fs.dto.account.AccountDto;
import io.github.thepragmaticsquad.fs.enums.TransactionType;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccounts();

    List<AccountDetailedDto> getAllAccountsDetailed();

    List<AccountAbstractedDto> getAllAccountsAbstracted();

    Long createAccount(AccountDetailedDto account);

    AccountDto getAccount(Long id);

    AccountDetailedDto getAccountDetailed(Long id);

    AccountAbstractedDto getAccountAbstracted(Long id);

    AccountDto updateAccount(Long id, AccountDetailedDto account);

    void deleteAccount(Long id);

    void addTransaction(Long accountId, TransactionType type, BigDecimal amount);

}
