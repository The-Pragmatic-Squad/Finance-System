package io.github.thepragmaticsquad.fs.service;

import io.github.thepragmaticsquad.fs.dto.AccountAbstractedDto;
import io.github.thepragmaticsquad.fs.dto.AccountDetailedDto;
import io.github.thepragmaticsquad.fs.dto.AccountDto;
import io.github.thepragmaticsquad.fs.enums.TransactionType;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccounts();

    List<AccountDetailedDto> getAllAccountsDetailed();

    List<AccountAbstractedDto> getAllAccountsAbstracted();

    //     TODO Return Id
    Long createAccount(AccountDetailedDto account);

    //     TODO GetAccount
    AccountDto getAccount(Long id);
//     TODO GetAccountDetailed

    AccountDetailedDto getAccountDetailed(Long id);

    AccountAbstractedDto getAccountAbstracted(Long id);

    AccountDto updateAccount(Long id, AccountDetailedDto account);

    void deleteAccount(Long id);

    void addTransaction(Long accountId, TransactionType type, BigDecimal amount);

}
