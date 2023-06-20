package io.github.thepragmaticsquad.fs.controller;

import io.github.thepragmaticsquad.fs.dto.AccountAbstractedDto;
import io.github.thepragmaticsquad.fs.dto.AccountDetailedDto;
import io.github.thepragmaticsquad.fs.dto.AccountDto;
import io.github.thepragmaticsquad.fs.entity.Account;
import io.github.thepragmaticsquad.fs.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;
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

    public AccountAbstractedDto getSpecificAccountAbstracted(Long id) {
        return null;
    }

    public AccountDetailedDto updateAccount(AccountDetailedDto account) {
        return null;
    }

    public void deleteAccount(Long id) {
    }

    public boolean isAccountActive(Long id) {
        return false;
    }

    public void deposit(Long id, BigDecimal amount) {

    }

    public void withdraw(Long id, BigDecimal amount) {

    }

}
