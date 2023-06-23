package io.github.thepragmaticsquad.fs.controller;

import io.github.thepragmaticsquad.fs.dto.account.AccountAbstractedDto;
import io.github.thepragmaticsquad.fs.dto.account.AccountDetailedDto;
import io.github.thepragmaticsquad.fs.dto.account.AccountDto;
import io.github.thepragmaticsquad.fs.enums.TransactionType;
import io.github.thepragmaticsquad.fs.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/account_detailed")
    public List<AccountDetailedDto> getAllAccountsDetailed() {
        return accountService.getAllAccountsDetailed();
    }

    @GetMapping("/account_abstract")
    public List<AccountAbstractedDto> getAllAccountsAbstracted() {
        return accountService.getAllAccountsAbstracted();
    }

    @PostMapping
    public Long createAccount(@RequestBody AccountDetailedDto account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public AccountDto getAccount(@PathVariable("id") Long id) {
        return accountService.getAccount(id);
    }

    @GetMapping("/detailed/{id}")
    public AccountDetailedDto getAccountDetailed(@PathVariable("id") Long id) {
        return accountService.getAccountDetailed(id);
    }

    @GetMapping("/abstracted/{id}")
    public AccountAbstractedDto getAccountAbstracted(@PathVariable("id") Long id) {
        return accountService.getAccountAbstracted(id);
    }

    @PutMapping("/{id}")
    public AccountDto updateAccount(@PathVariable("id") Long id, @RequestBody AccountDetailedDto account) {
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
    }

    @PostMapping("/transaction/{id}")
    public void addTransaction(@PathVariable("id") Long id, @RequestParam TransactionType type, @RequestParam BigDecimal amount) {
        accountService.addTransaction(id, type, amount);
    }


}
