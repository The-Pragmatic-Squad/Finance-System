package io.github.thepragmaticsquad.fs.service;

import io.github.thepragmaticsquad.fs.dto.AccountAbstractedDto;
import io.github.thepragmaticsquad.fs.dto.AccountDetailedDto;
import io.github.thepragmaticsquad.fs.dto.AccountDto;
import io.github.thepragmaticsquad.fs.dto.TransactionDetailedDto;
import io.github.thepragmaticsquad.fs.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccounts();

    List<AccountDetailedDto> getAllAccountsDetailed();

    List<AccountAbstractedDto> getAllAccountsAbstracted();

    AccountDetailedDto createAccount(Account account);

    AccountDto getSpecificAccount(Long id);

    AccountDetailedDto getSpecificAccountDetailed(Long id);

    AccountAbstractedDto getSpecificAccountAbstracted(Long id);

    AccountDetailedDto updateAccount(Long id , AccountDetailedDto account);

    void deleteAccount(Long id);

    void deposit(Long id, BigDecimal amount);

    void withdraw(Long id, BigDecimal amount);


}
