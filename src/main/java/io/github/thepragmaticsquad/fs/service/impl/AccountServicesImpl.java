package io.github.thepragmaticsquad.fs.service.impl;

import io.github.thepragmaticsquad.fs.dto.account.AccountAvatarDto;
import io.github.thepragmaticsquad.fs.dto.account.AccountDto;
import io.github.thepragmaticsquad.fs.dto.account.CreateAccountDto;
import io.github.thepragmaticsquad.fs.dto.account.UpdateAccountDto;
import io.github.thepragmaticsquad.fs.dto.transaction.CreateTransactionDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailsDto;
import io.github.thepragmaticsquad.fs.entity.Account;
import io.github.thepragmaticsquad.fs.entity.creditcard.CardIssuer;

import io.github.thepragmaticsquad.fs.exception.account.AccountAlreadyExistException;
import io.github.thepragmaticsquad.fs.exception.account.AccountNotFoundException;
import io.github.thepragmaticsquad.fs.mapper.AccountMapper;
import io.github.thepragmaticsquad.fs.repository.AccountRepository;
import io.github.thepragmaticsquad.fs.service.AccountService;
import io.github.thepragmaticsquad.fs.service.TransactionService;


import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@Slf4j
public class AccountServicesImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final TransactionService transactionService;
    private final CardIssuer cardIssuer;
    private static final Logger logger = LoggerFactory.getLogger(AccountServicesImpl.class);

    public AccountServicesImpl(AccountRepository accountRepository, TransactionService transactionService, CardIssuer cardIssuer) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
        this.cardIssuer = cardIssuer;
    }


    public Long createAccount(CreateAccountDto account) {

        Optional<Account> isAccountExist = accountRepository.findAccountByEmailOrUsername(account.getEmail(), account.getUsername());

        if (isAccountExist.isPresent()) {
            throw new AccountAlreadyExistException();
        }


        Account savedAccount = AccountMapper.INSTANCE.toAccount(account);
        if (account.getBalance() == null) {
            account.setBalance(BigDecimal.ZERO);
        }
        savedAccount.setCreatedAt(LocalDateTime.now());
        savedAccount.setActive(account.getBalance().compareTo(BigDecimal.ZERO) > 0);
        savedAccount.setCreditNumber(cardIssuer.issueCreditCard());

        savedAccount.setBalance(account.getBalance());
        savedAccount = accountRepository.save(savedAccount);

        return savedAccount.getId();
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(AccountMapper.INSTANCE::toAccountDto)
                .toList();
    }



    public AccountDto getAccount(Long id) {
        Account account = accountRepository.findAccountByIdIs(id)
                .orElseThrow(AccountNotFoundException::new);

        return AccountMapper.INSTANCE.toAccountDto(account);
    }



    public AccountAvatarDto getAccountAvatar(Long id) {
        Account account = accountRepository.findAccountByIdIs(id)
                .orElseThrow(AccountNotFoundException::new);

        return AccountMapper.INSTANCE.toAvatarDto(account);
    }

    public AccountDto updateAccount(Long id, UpdateAccountDto accountDto) {
        Account account = accountRepository.findAccountByIdIs(id)
                .orElseThrow(AccountNotFoundException::new);

        AccountMapper.INSTANCE.updateAccountFromDto(account, accountDto);
        account = accountRepository.save(account);
        return AccountMapper.INSTANCE.toAccountDto(account);
    }

    public void deleteAccount(Long id) {
        Account account = accountRepository.findAccountByIdIs(id)
                .orElseThrow(AccountNotFoundException::new);

        account.setActive(false);
        accountRepository.save(account);
    }
    @Override
    public Page<TransactionDetailsDto> getTransactionsByAccountId(Long id, int page, int size) {
        return transactionService.getTransactionsByAccountId(id, page, size);
    }



    @Transactional
    public TransactionDetailsDto processTransaction(CreateTransactionDto transactionDto) {
        Account account = accountRepository.findAccountByIdIs(transactionDto.getAccountId())
                    .orElseThrow(AccountNotFoundException::new);

        TransactionDetailsDto transactionDetailsDto = transactionService.processTransaction(account, transactionDto);

        account.setLastTransaction(transactionDetailsDto.getDate());
        account.setBalance(transactionDetailsDto.getBalanceAfter());
        if (account.getBalance().compareTo(BigDecimal.ZERO) >= 0) {
            account.setActive(true);
        }

        accountRepository.save(account);

        return transactionDetailsDto;
    }


}
