package io.github.thepragmaticsquad.fs.service.impl;

import io.github.thepragmaticsquad.fs.dto.transaction.CreateTransactionDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailsDto;
import io.github.thepragmaticsquad.fs.entity.Account;
import io.github.thepragmaticsquad.fs.entity.Transaction;
import io.github.thepragmaticsquad.fs.enums.AccountType;
import io.github.thepragmaticsquad.fs.enums.TransactionStatus;
import io.github.thepragmaticsquad.fs.enums.TransactionType;
import io.github.thepragmaticsquad.fs.mapper.TransactionMapper;
import io.github.thepragmaticsquad.fs.repository.TransactionsRepository;
import io.github.thepragmaticsquad.fs.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final BigDecimal VIP_ACCOUNT_BALANCE_LIMIT = new BigDecimal(-10000);
    private static final BigDecimal STANDARD_ACCOUNT_BALANCE_LIMIT = new BigDecimal(-10000);
    private final TransactionsRepository transactionsRepository;

    public TransactionServiceImpl(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public List<TransactionDetailsDto> getAllTransactions() {
        List<Transaction> transactions = transactionsRepository.findAll();
        return transactions.stream()
                .map(TransactionMapper.INSTANCE::toDetailsDto)
                .toList();
    }

    public TransactionDetailsDto getTransactionById(Long id) {
        Transaction transaction = transactionsRepository.findById(id).orElseThrow();
        return TransactionMapper.INSTANCE.toDetailsDto(transaction);
    }

    public Page<TransactionDetailsDto> getTransactionsByAccountId(Long accountId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Transaction> transactions = transactionsRepository.findAllByAccountId(accountId, pageable);
        return transactions.map(TransactionMapper.INSTANCE::toDetailsDto);
    }


    @Transactional
    public TransactionDetailsDto processTransaction(Account account, CreateTransactionDto transactionDto) {
        Transaction saveTransaction = TransactionMapper.INSTANCE.toTransaction(transactionDto);
        BigDecimal amount = transactionDto.getAmount();
        TransactionType transactionType = transactionDto.getType();

        saveTransaction.setType(transactionType);
        saveTransaction.setAmount(amount);

        saveTransaction.setAccount(account);
        saveTransaction.setDate(LocalDateTime.now());
        saveTransaction.setBalanceBefore(account.getBalance());
        saveTransaction.setStatus(TransactionStatus.SUCCESS);

        if (Objects.equals(transactionType, TransactionType.DEPOSIT)) {
            saveTransaction.setBalanceBefore(account.getBalance());
            BigDecimal newBalance = account.getBalance().add(amount);
            saveTransaction.setBalanceAfter(newBalance);
        } else if (Objects.equals(transactionType, TransactionType.WITHDRAWAL)) {
            BigDecimal balanceAfter = account.getBalance().subtract(amount);

            if (account.getType() == AccountType.STANDARD && balanceAfter.compareTo(STANDARD_ACCOUNT_BALANCE_LIMIT) >= 0 && account.isActive()) {
                saveTransaction.setBalanceAfter(balanceAfter);
            } else if (account.getType() == AccountType.VIP && balanceAfter.compareTo(VIP_ACCOUNT_BALANCE_LIMIT) >= 0 && account.isActive()) {
                saveTransaction.setBalanceAfter(balanceAfter);
            } else {
                saveTransaction.setStatus(TransactionStatus.FAILED);
                saveTransaction.setBalanceAfter(account.getBalance());
            }
        }

        saveTransaction = transactionsRepository.save(saveTransaction);

        return TransactionMapper.INSTANCE.toDetailsDto(saveTransaction);
    }
}
