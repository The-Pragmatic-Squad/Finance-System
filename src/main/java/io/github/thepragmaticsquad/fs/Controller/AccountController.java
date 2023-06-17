package io.github.thepragmaticsquad.fs.Controller;

import io.github.thepragmaticsquad.fs.Dto.AccountDto;
import io.github.thepragmaticsquad.fs.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
