package io.github.thepragmaticsquad.fs.service;

import io.github.thepragmaticsquad.fs.dto.TransactionDetailedDto;
import io.github.thepragmaticsquad.fs.dto.TransactionDto;
import io.github.thepragmaticsquad.fs.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    Long processTransaction(TransactionDetailedDto transaction);
    TransactionDto createTransaction(Transaction transaction);
    TransactionDetailedDto getTransactionById(Long id);

    List<TransactionDto> getAllTransactions();

}
