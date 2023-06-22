package io.github.thepragmaticsquad.fs.service;

import io.github.thepragmaticsquad.fs.dto.transaction.SimpleTransactionDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailedDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    Long processTransaction(SimpleTransactionDto transaction);

    TransactionDetailedDto getTransactionById(Long id);

    List<TransactionDto> getAllTransactions();

    void deleteTransactionById(Long id);
}
