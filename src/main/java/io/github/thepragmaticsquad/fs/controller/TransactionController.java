package io.github.thepragmaticsquad.fs.controller;

import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailsDto;

import io.github.thepragmaticsquad.fs.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {


    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionDetailsDto> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public TransactionDetailsDto getTransactionById(@PathVariable("id") Long id) {
        return transactionService.getTransactionById(id);
    }
    @GetMapping("/account/{accountId}")
    List<TransactionDetailsDto> getTransactionsByAccountId(@PathVariable("accountId")Long accountId){
        return transactionService.getTransactionsByAccountId(accountId);
    }

}
