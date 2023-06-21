package io.github.thepragmaticsquad.fs.service.impl;

import io.github.thepragmaticsquad.fs.dto.transaction.SimpleTransactionModel;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailedDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDto;
import io.github.thepragmaticsquad.fs.entity.Account;
import io.github.thepragmaticsquad.fs.entity.Transaction;
import io.github.thepragmaticsquad.fs.enums.AccountType;
import io.github.thepragmaticsquad.fs.mapper.TransactionMapper;
import io.github.thepragmaticsquad.fs.repository.AccountRepository;
import io.github.thepragmaticsquad.fs.repository.TransactionsRepository;
import io.github.thepragmaticsquad.fs.service.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionsRepository transactionsRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionsRepository transactionsRepository, AccountRepository accountRepository) {
        this.transactionsRepository = transactionsRepository;
        this.accountRepository = accountRepository;
    }

    public TransactionDto createTransaction(Transaction transaction) {
       Transaction savedTransaction = transactionsRepository.save(transaction);

        return TransactionMapper.INSTANCE.toDto(savedTransaction);
    }

//    @Override
//    public Long processTransaction(TransactionDetailedDto transactionDetailedDto) {
//        Transaction transaction = TransactionMapper.INSTANCE.toTransaction(transactionDetailedDto);
//        Account account = accountRepository.findById(transaction.getAccount().getId()).orElseThrow(
//        );
//
//        BigDecimal balance = account.getBalance();
//        BigDecimal transactionAmount = transaction.getAmount();
//
//        if (transactionAmount.compareTo(balance) < 0 && account.getType() == AccountType.STANDARD)
//            throw new RuntimeException();
//
//        account.setBalance(balance.subtract(transactionAmount));
//        accountRepository.save(account);
//
//        transaction = transactionsRepository.save(transaction);
//
//        return transaction.getId();
//    }

    @Override
    public Long processTransaction(SimpleTransactionModel simpleTransactionModel) {
        Transaction transaction = TransactionMapper.INSTANCE.toTransaction(simpleTransactionModel);
        Account account = accountRepository.findById(transaction.getAccount().getId()).orElseThrow(
        );

        BigDecimal balance = account.getBalance();
        BigDecimal transactionAmount = transaction.getAmount();

        if (transactionAmount.compareTo(balance) < 0 && account.getType() == AccountType.STANDARD)
            throw new RuntimeException();

        account.setBalance(balance.subtract(transactionAmount));
        accountRepository.save(account);

        transaction = transactionsRepository.save(transaction);

        return transaction.getId();

    }

    public TransactionDetailedDto getTransactionById(Long id) {
        Transaction transaction = transactionsRepository.findById(id).orElseThrow();
        return TransactionMapper.INSTANCE.toDetailedDto(transaction);
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        return null;
    }

    @Override
    public void deleteTransactionById(Long id) {

    }
}
