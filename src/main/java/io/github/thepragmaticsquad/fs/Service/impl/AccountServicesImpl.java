package io.github.thepragmaticsquad.fs.service.impl;

import io.github.thepragmaticsquad.fs.dto.*;
import io.github.thepragmaticsquad.fs.entity.Account;
import io.github.thepragmaticsquad.fs.mapper.AccountMapper;
import io.github.thepragmaticsquad.fs.repository.AccountRepository;
import io.github.thepragmaticsquad.fs.service.AccountService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServicesImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServicesImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountDetailedDto createAccount(AccountDetailedDto account) {
        return null;
    }

    @Override
    public AccountDetailedDto getAccountById(Long id) {
        return null;
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAllActiveAccounts();
        return accounts.stream()
                .map(data-> accountMapper.toDto(data))
                .collect(Collectors.toList());
    }

//    @Override
//    public List<AccountAbstractedDto> getAllTransactionAccounts() {
//        List<Account> accounts = accountRepository.findAll();
//        return accounts.stream()
//                .map(AccountMappe::toAccountAbstractDto)
//                .collect(Collectors.toList());    }

    @Override
    public AccountDetailedDto updateAccount(AccountDetailedDto account) {
        return null;
    }

    @Override
    public void deleteAccount(Long id) {

    }

    @Override
    public boolean isAccountActive(Long id) {
        return false;
    }

    @Override
    public boolean isSufficientBalance(Long id, Double amount) {
        return false;
    }

    @Override
    public boolean isCreditCardNumberValid(String credit_number) {
        return false;
    }

    @Override
    public boolean isEmailValid(String email) {
        return false;
    }

    @Override
    public boolean isPhoneNumberValid(String phone) {
        return false;
    }

    @Override
    public void deposit(Long accountId, Double amount) {

    }

    @Override
    public void withdraw(Long accountId, Double amount) {

    }

    @Override
    public void transfer(Long fromAccountId, Long toAccountId, Double amount) {

    }

    @Override
    public List<TransactionDetailedDto> getTransactionsByAccount(Long accountId) {
        return null;
    }
}
