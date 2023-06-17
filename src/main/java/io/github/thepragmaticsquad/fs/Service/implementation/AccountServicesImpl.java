package io.github.thepragmaticsquad.fs.Service.implementation;

import io.github.thepragmaticsquad.fs.Dto.AccountDetailedDto;
import io.github.thepragmaticsquad.fs.Dto.AccountDto;
import io.github.thepragmaticsquad.fs.Dto.TransactionDetailedDto;
import io.github.thepragmaticsquad.fs.Entity.Account;
import io.github.thepragmaticsquad.fs.Mapper.AccountMapper;
import io.github.thepragmaticsquad.fs.Repository.AccountRepository;
import io.github.thepragmaticsquad.fs.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServicesImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

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
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(AccountMapper::toAccountDto)
                .collect(Collectors.toList());
    }

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
