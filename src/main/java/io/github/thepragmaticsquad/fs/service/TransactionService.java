package io.github.thepragmaticsquad.fs.service;

import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailedDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    TransactionDetailedDto getTransactionById(Long id);

    List<TransactionDto> getAllTransactions();

    List<TransactionDto> getTransactionsByAccountId(Long accountId);
}
