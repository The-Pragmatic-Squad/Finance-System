package io.github.thepragmaticsquad.fs.service;

import io.github.thepragmaticsquad.fs.dto.transaction.CreateTransactionDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailsDto;
import io.github.thepragmaticsquad.fs.entity.Account;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    TransactionDetailsDto getTransactionById(Long id);

    List<TransactionDetailsDto> getAllTransactions();

    List<TransactionDetailsDto> getTransactionsByAccountId(Long accountId);

    TransactionDetailsDto processTransaction(Account account, CreateTransactionDto createTransactionDto);
}
