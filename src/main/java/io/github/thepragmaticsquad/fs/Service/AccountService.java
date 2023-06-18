package io.github.thepragmaticsquad.fs.service;

import io.github.thepragmaticsquad.fs.dto.*;
import java.util.List;

public interface AccountService {
    public AccountDetailedDto createAccount(AccountDetailedDto account);
    public AccountDetailedDto getAccountById(Long id);
    public List<AccountDto> getAllAccounts();
//    public List<AccountAbstractedDto> getAllTransactionAccounts();
    public AccountDetailedDto updateAccount(AccountDetailedDto account);
    public void deleteAccount(Long id);
    public boolean isAccountActive(Long id);
    public boolean isSufficientBalance(Long id, Double amount);
    public boolean isCreditCardNumberValid(String credit_number);
    public boolean isEmailValid(String email);
    public boolean isPhoneNumberValid(String phone);
    public void deposit(Long accountId, Double amount);
    public void withdraw(Long accountId, Double amount);
    public void transfer(Long fromAccountId, Long toAccountId, Double amount);
    public List<TransactionDetailedDto> getTransactionsByAccount(Long accountId);
}
