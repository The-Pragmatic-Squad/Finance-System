package io.github.thepragmaticsquad.fs.controller;

import io.github.thepragmaticsquad.fs.dto.TransactionDetailedDto;
import io.github.thepragmaticsquad.fs.dto.TransactionDto;
import io.github.thepragmaticsquad.fs.entity.Transaction;
import io.github.thepragmaticsquad.fs.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {


    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
//    @PostMapping
//    public Long processTransaction(@RequestBody TransactionDetailedDto transactionDetailedDto) {
//        return transactionService.processTransaction(transactionDetailedDto);
//    }
    @PostMapping
    public TransactionDto createTransaction(@RequestBody Transaction transaction){
        return transactionService.createTransaction(transaction);
    }
    @GetMapping
    public List<TransactionDto> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public TransactionDetailedDto getTransactionById(@PathVariable("id") Long id) {
        return transactionService.getTransactionById(id);
    }



}
