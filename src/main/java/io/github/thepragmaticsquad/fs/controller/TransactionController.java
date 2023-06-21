package io.github.thepragmaticsquad.fs.controller;

import io.github.thepragmaticsquad.fs.dto.transaction.SimpleTransactionModel;
import io.github.thepragmaticsquad.fs.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Long processTransaction(@RequestBody SimpleTransactionModel transactionDetailedDto) {
        return transactionService.processTransaction(transactionDetailedDto);
    }
}