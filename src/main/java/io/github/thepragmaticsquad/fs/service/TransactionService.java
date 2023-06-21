package io.github.thepragmaticsquad.fs.service;

import io.github.thepragmaticsquad.fs.dto.transaction.SimpleTransactionModel;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailedDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    Long processTransaction(SimpleTransactionModel transaction);

    TransactionDetailedDto getTransactionById(Long id);

    List<TransactionDto> getAllTransactions();

    void deleteTransactionById(Long id);
}
