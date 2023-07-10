package io.github.thepragmaticsquad.fs.service;

import io.github.thepragmaticsquad.fs.dto.transaction.CreateTransactionDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailsDto;
import io.github.thepragmaticsquad.fs.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    TransactionDetailsDto getTransactionById(Long id);

    List<TransactionDetailsDto> getAllTransactions();

    Page<TransactionDetailsDto> getTransactionsByAccountId(Long accountId, int page, int size);

    TransactionDetailsDto processTransaction(Account account, CreateTransactionDto createTransactionDto);
}
