package io.github.thepragmaticsquad.fs.controller;

import io.github.thepragmaticsquad.fs.dto.account.AccountAvatarDto;
import io.github.thepragmaticsquad.fs.dto.account.AccountDto;
import io.github.thepragmaticsquad.fs.dto.account.CreateAccountDto;
import io.github.thepragmaticsquad.fs.dto.account.UpdateAccountDto;
import io.github.thepragmaticsquad.fs.dto.transaction.CreateTransactionDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailsDto;
import io.github.thepragmaticsquad.fs.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountDto account) {
        Long createdAccountId = accountService.createAccount(account);
        String uri = String.format("/accounts/%s", createdAccountId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(uri));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public AccountDto getAccount(@PathVariable("id") Long id) {
        return accountService.getAccount(id);
    }

    @GetMapping("/avatar/{id}")
    public AccountAvatarDto getAccountAvatar(@PathVariable("id") Long id) {
        return accountService.getAccountAvatar(id);
    }

    @PutMapping("/{id}")
    public AccountDto updateAccount(@PathVariable("id") Long id, @RequestBody @Valid UpdateAccountDto account) {
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
    }

    @PostMapping("{id}/transactions")
    public ResponseEntity<String> processTransaction(@PathVariable("id") Long id, @RequestBody @Valid CreateTransactionDto transactionDto) {
        transactionDto.setAccountId(id);
        accountService.processTransaction(transactionDto);
    }
    @GetMapping("{id}/transactions")
    public List<TransactionDetailsDto> getTransactionsByAccountId(@PathVariable("id") Long id){
        return accountService.getTransactionsByAccountId(id);
    }
}
