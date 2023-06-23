package io.github.thepragmaticsquad.fs.service.impl;

import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailedDto;
import io.github.thepragmaticsquad.fs.entity.Transaction;
import io.github.thepragmaticsquad.fs.mapper.TransactionMapper;
import io.github.thepragmaticsquad.fs.repository.TransactionsRepository;
import io.github.thepragmaticsquad.fs.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionsRepository transactionsRepository;


    public TransactionServiceImpl(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions = transactionsRepository.findAll();
        return transactions.stream()
                .map(TransactionMapper.INSTANCE::toDto)
                .toList();
    }

    public TransactionDetailedDto getTransactionById(Long id) {
        Transaction transaction = transactionsRepository.findById(id).orElseThrow();
        return TransactionMapper.INSTANCE.toDetailedDto(transaction);
    }

    public List<TransactionDto> getTransactionsByAccountId(Long accountId) {
        List<Transaction> transactions = transactionsRepository.findAllByAccount_Id(accountId);
        return transactions.stream()
                .map(TransactionMapper.INSTANCE::toDto)
                .toList();
    }
}
