package io.github.thepragmaticsquad.fs.service.impl;

import io.github.thepragmaticsquad.fs.dto.AccountAbstractedDto;
import io.github.thepragmaticsquad.fs.dto.AccountDetailedDto;
import io.github.thepragmaticsquad.fs.dto.AccountDto;
import io.github.thepragmaticsquad.fs.entity.Account;
import io.github.thepragmaticsquad.fs.enums.AccountType;
import io.github.thepragmaticsquad.fs.mapper.AccountMapper;
import io.github.thepragmaticsquad.fs.repository.AccountRepository;
import io.github.thepragmaticsquad.fs.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServicesImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountDetailedDto createAccount(Account account) {
        account.setCreatedAt(LocalDateTime.now());
        account.setType(AccountType.STANDARD);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.INSTANCE.toDetailedDto(savedAccount);
    }
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAccountsByActiveTrue();
        return accounts.stream()
                .map(AccountMapper.INSTANCE::toDto)
                .toList();
    }
    
    public List<AccountDetailedDto> getAllAccountsDetailed() {
        List<Account> accounts = accountRepository.findAccountsByActiveTrue();
        return accounts.stream()
                .map(AccountMapper.INSTANCE::toDetailedDto)
                .toList();
    }

    public List<AccountAbstractedDto> getAllAccountsAbstracted() {
        List<Account> accounts = accountRepository.findAccountsByActiveTrue();
        return accounts.stream()
                .map(AccountMapper.INSTANCE::toAbstractedDto)
                .toList();
    }


    public AccountDto getSpecificAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.INSTANCE.toDto(account);
    }

    public AccountDetailedDto getSpecificAccountDetailed(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.INSTANCE.toDetailedDto(account);
    }

    public AccountAbstractedDto getSpecificAccountAbstracted(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.INSTANCE.toAbstractedDto(account);
    }

    public AccountDetailedDto updateAccount(Long id, AccountDetailedDto accountDto) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));


        if (accountDto.getUsername() != null) {
            account.setUsername(accountDto.getUsername());
        }

        if (accountDto.getEmail() != null) {
            account.setEmail(accountDto.getEmail());
        }

        if (accountDto.getPhone() != null) {
            account.setPhone(accountDto.getPhone());
        }

        if (accountDto.getCreditNumber() != null) {
            account.setCreditNumber(accountDto.getCreditNumber());
        }

        if (accountDto.getBalance() != null) {
            account.setBalance(accountDto.getBalance());
        }

        if (accountDto.getType() != null) {
            account.setType(accountDto.getType());
        }

        account = accountRepository.save(account);

        return AccountMapper.INSTANCE.toDetailedDto(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found or already deleted"));
        accountRepository.deleteById(id);
    }

    public void deposit(Long id, BigDecimal amount) {
        Account account = accountRepository.findAccountByIdAndActiveTrue(id).orElseThrow(()-> new RuntimeException("Account not found or Deactivated"));
        BigDecimal balance = account.getBalance();
        BigDecimal deposit = balance.add(amount);
        account.setBalance(deposit);
        account.setLastTransaction(LocalDateTime.now());
        accountRepository.save(account);
    }
    public void withdraw(Long id, BigDecimal amount) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not found"));
        BigDecimal balance = account.getBalance();
        BigDecimal withdraw = balance.subtract(amount);
        account.setBalance(withdraw);
        account.setLastTransaction(LocalDateTime.now());
        accountRepository.save(account);
    }
}
