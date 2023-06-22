package io.github.thepragmaticsquad.fs.controller;

import io.github.thepragmaticsquad.fs.dto.AccountAbstractedDto;
import io.github.thepragmaticsquad.fs.dto.AccountDetailedDto;
import io.github.thepragmaticsquad.fs.dto.AccountDto;
import io.github.thepragmaticsquad.fs.entity.Account;
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
    public List<AccountDto> getAllAccounts(){
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
    public AccountDetailedDto createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public AccountDto getSpecificAccount(@PathVariable("id") Long id) {
        return accountService.getSpecificAccount(id);
    }

    @GetMapping("/detailed/{id}")
    public AccountDetailedDto getSpecificAccountDetailed(@PathVariable("id") Long id) {
        return accountService.getSpecificAccountDetailed(id);
    }

    @GetMapping("/abstracted/{id}")
    public AccountAbstractedDto getSpecificAccountAbstracted(@PathVariable("id") Long id) {
        return accountService.getSpecificAccountAbstracted(id);
    }

    @PutMapping("/{id}")
    public AccountDetailedDto updateAccount(@PathVariable("id") Long id, @RequestBody AccountDetailedDto account) {
        return accountService.updateAccount(id,account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable("id")  Long id) {
        accountService.deleteAccount(id);
    }

    @PutMapping("/deposit/{id}")
    public void deposit(@PathVariable("id") Long id,@RequestParam BigDecimal amount) {
        accountService.deposit(id, amount);
    }

    public void withdraw(Long id, BigDecimal amount) {

    }

}
