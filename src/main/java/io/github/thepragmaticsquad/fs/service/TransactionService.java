package io.github.thepragmaticsquad.fs.service;

import io.github.thepragmaticsquad.fs.dto.TransactionDetailedDto;
import io.github.thepragmaticsquad.fs.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    TransactionDetailedDto getTransactionById(Long id);

    List<TransactionDto> getAllTransactions();

    List<TransactionDto> getTransactionsByAccountId(Long accountId);
}
