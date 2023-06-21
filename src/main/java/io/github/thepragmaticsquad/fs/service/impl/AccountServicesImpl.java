package io.github.thepragmaticsquad.fs.service.impl;

import io.github.thepragmaticsquad.fs.dto.account.AccountAbstractedDto;
import io.github.thepragmaticsquad.fs.dto.account.AccountDetailedDto;
import io.github.thepragmaticsquad.fs.dto.account.AccountDto;
import io.github.thepragmaticsquad.fs.entity.Account;
import io.github.thepragmaticsquad.fs.mapper.AccountMapper;
import io.github.thepragmaticsquad.fs.repository.AccountRepository;
import io.github.thepragmaticsquad.fs.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServicesImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServicesImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDetailedDto createAccount(Account account) {
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.INSTANCE.toDetailedDto(savedAccount);
    }

    public AccountDto getSpecificAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        return AccountMapper.INSTANCE.toDto(account);
    }

    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAllActiveAccounts();
        return accounts.stream()
                .map(AccountMapper.INSTANCE::toDto)
                .toList();
    }

    public List<AccountDetailedDto> getAllAccountsDetailed() {
        List<Account> accounts = accountRepository.findAllActiveAccounts();
        return accounts.stream()
                .map(AccountMapper.INSTANCE::toDetailedDto)
                .toList();
    }

    public List<AccountAbstractedDto> getAllAccountsAbstracted() {
        List<Account> accounts = accountRepository.findAllActiveAccounts();
        return accounts.stream()
                .map(AccountMapper.INSTANCE::toAbstractedDto)
                .toList();
    }



    public AccountDetailedDto getSpecificAccountDetailed(Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        return AccountMapper.INSTANCE.toDetailedDto(account);
    }

    public AccountAbstractedDto getSpecificAccountAbstracted(Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        return AccountMapper.INSTANCE.toAbstractedDto(account);
    }

    public AccountDetailedDto updateAccount(AccountDetailedDto accountDto) {
        Account account = accountRepository.findById(accountDto.getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        AccountMapper.INSTANCE.updateAccountFromDto(accountDto, account);
        account = accountRepository.save(account);
        return AccountMapper.INSTANCE.toDetailedDto(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public boolean isAccountActive(Long id) {
        return accountRepository.getAccountByIdAndActiveTrue(id);
    }

    public void deposit(Long id, BigDecimal amount) {
        Account account = accountRepository.findById(id).orElseThrow();
        BigDecimal balance = account.getBalance();
        BigDecimal deposit = balance.add(amount);
        account.setBalance(deposit);
        accountRepository.save(account);
    }
    public void withdraw(Long id, BigDecimal amount) {
        Account account = accountRepository.findById(id).orElseThrow();
        BigDecimal balance = account.getBalance();
        BigDecimal withdraw = balance.subtract(amount);
        account.setBalance(withdraw);
        accountRepository.save(account);
    }
}
