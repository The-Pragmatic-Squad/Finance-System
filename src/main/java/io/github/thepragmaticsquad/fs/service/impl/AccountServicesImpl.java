package io.github.thepragmaticsquad.fs.service.impl;

import io.github.thepragmaticsquad.fs.dto.account.AccountAbstractedDto;
import io.github.thepragmaticsquad.fs.dto.account.AccountDetailedDto;
import io.github.thepragmaticsquad.fs.dto.account.AccountDto;
import io.github.thepragmaticsquad.fs.dto.transaction.SimpleTransactionDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailedDto;
import io.github.thepragmaticsquad.fs.entity.Account;
import io.github.thepragmaticsquad.fs.entity.Transaction;
import io.github.thepragmaticsquad.fs.enums.AccountType;
import io.github.thepragmaticsquad.fs.enums.TransactionStatus;
import io.github.thepragmaticsquad.fs.enums.TransactionType;
import io.github.thepragmaticsquad.fs.mapper.AccountMapper;
import io.github.thepragmaticsquad.fs.mapper.TransactionMapper;
import io.github.thepragmaticsquad.fs.repository.AccountRepository;
import io.github.thepragmaticsquad.fs.repository.TransactionsRepository;
import io.github.thepragmaticsquad.fs.service.AccountService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServicesImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionsRepository transactionsRepository;

    public AccountServicesImpl(AccountRepository accountRepository, TransactionsRepository transactionsRepository) {
        this.accountRepository = accountRepository;
        this.transactionsRepository = transactionsRepository;
    }

    public static String generateRandomNumber(int digits) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public Long createAccount(AccountDetailedDto account) {
        Account savedAccount = AccountMapper.INSTANCE.toAccount(account);
        savedAccount.setCreatedAt(LocalDateTime.now());
        // TODO Could be fixed by mapping
        String accountTypeString = String.valueOf(account.getType());
        AccountType accountType = accountTypeString != null ? AccountType.valueOf(accountTypeString) : AccountType.STANDARD;
        savedAccount.setType(accountType);

        // Set credit number to a random value if it is not provided
        Optional<String> creditNumber = Optional.ofNullable(account.getCreditNumber()).filter(s -> !s.isBlank());
        if (creditNumber.isEmpty()) {
            String randomNumber = generateRandomNumber(30);
            savedAccount.setCreditNumber(randomNumber);
        }

        // Set balance to zero if it is not provided
        BigDecimal balance = account.getBalance() != null ? account.getBalance() : BigDecimal.ZERO;
        savedAccount.setBalance(balance);

        // TODO: Want to check if the account exists or not

        accountRepository.save(savedAccount);
        return AccountMapper.INSTANCE.toAccountDto(savedAccount).getId();
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAccountsByActiveTrue();
        return accounts.stream()
                .map(AccountMapper.INSTANCE::toAccountDto)
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


    public AccountDto getAccount(Long id) {
        Account account = accountRepository.findAccountByIdAndActiveTrue(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.INSTANCE.toAccountDto(account);
    }

    public AccountDetailedDto getAccountDetailed(Long id) {
        Account account = accountRepository.findAccountByIdAndActiveTrue(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.INSTANCE.toDetailedDto(account);
    }

    public AccountAbstractedDto getAccountAbstracted(Long id) {
        Account account = accountRepository.findAccountByIdAndActiveTrue(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.INSTANCE.toAbstractedDto(account);
    }

    public AccountDto updateAccount(Long id, AccountDetailedDto accountDto) {
        Account account = accountRepository.findAccountByIdAndActiveTrue(id).orElseThrow(() -> new RuntimeException("Account not found"));


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

        return AccountMapper.INSTANCE.toAccountDto(account);
    }

    public void deleteAccount(Long id) {
        Account account = accountRepository.findAccountByIdAndActiveTrue(id).orElseThrow(() -> new RuntimeException("Account not found or already deleted"));
        account.setActive(false);
        accountRepository.save(account);
    }

    public void processTransaction(SimpleTransactionDto transactionDto) {
        Long accountId = transactionDto.getAccountId();
        BigDecimal amount = transactionDto.getAmount();
        TransactionType type = transactionDto.getType();

        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found or already deleted"));
        TransactionDetailedDto transaction = new TransactionDetailedDto();
        Transaction saveTransaction = TransactionMapper.INSTANCE.toTransaction(transaction);
        saveTransaction.setAccount(account);
        saveTransaction.setType(type);
        saveTransaction.setDate(LocalDateTime.now());
        saveTransaction.setAmount(amount);
        saveTransaction.setBalanceBefore(account.getBalance());
        saveTransaction.setStatus(TransactionStatus.SUCCESS);
        if (Objects.equals(type, TransactionType.DEPOSIT)) {
            account.setBalance(account.getBalance().add(amount));
        } else if (Objects.equals(type, TransactionType.WITHDRAWAL) && account.getType() == AccountType.STANDARD) {
            if (account.getBalance().compareTo(amount) >= 0) {
                account.setBalance(account.getBalance().subtract(amount));
            } else {
                saveTransaction.setStatus(TransactionStatus.FAILED);
            }
        } else if (Objects.equals(type, TransactionType.WITHDRAWAL) && account.getType() == AccountType.VIP) {
            account.setBalance(account.getBalance().subtract(amount));
        }
        account.setLastTransaction(LocalDateTime.now());
        accountRepository.save(account);
        saveTransaction.setBalanceAfter(account.getBalance());
        transactionsRepository.save(saveTransaction);
    }
}
