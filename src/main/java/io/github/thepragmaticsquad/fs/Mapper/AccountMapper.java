package io.github.thepragmaticsquad.fs.Mapper;


import io.github.thepragmaticsquad.fs.Dto.AccountDetailedDto;
import io.github.thepragmaticsquad.fs.Dto.AccountDto;
import io.github.thepragmaticsquad.fs.Entity.Account;
import io.github.thepragmaticsquad.fs.Entity.AccountType;
import org.mapstruct.Mapper;

@Mapper
public class AccountMapper {

    public static AccountDto toAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setUser_name(account.getUser_name());
        accountDto.setBalance(account.getBalance());
        accountDto.setCreatedAt(account.getCreatedAt());
        accountDto.setLastTransaction(account.getLastTransaction());
        accountDto.setType(AccountType.valueOf(account.getType().name()));
        accountDto.setActive(account.isActive());
        return accountDto;
    }

    public static AccountDetailedDto toAccountDetailedDto(Account account) {
        AccountDetailedDto accountDetailedDto = new AccountDetailedDto();
        accountDetailedDto.setId(account.getId());
        accountDetailedDto.setUser_name(account.getUser_name());
        accountDetailedDto.setEmail(account.getEmail());
        accountDetailedDto.setPhone(account.getPhone());
        accountDetailedDto.setCredit_number(account.getCredit_number());
        accountDetailedDto.setBalance(account.getBalance());
        accountDetailedDto.setCreatedAt(account.getCreatedAt());
        accountDetailedDto.setLastTransaction(account.getLastTransaction());
        accountDetailedDto.setType(AccountType.valueOf(account.getType().name()));
        accountDetailedDto.setActive(account.isActive());
        return accountDetailedDto;
    }

    public static Account toAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setUser_name(accountDto.getUser_name());
        account.setBalance(accountDto.getBalance());
        account.setCreatedAt(accountDto.getCreatedAt());
        account.setLastTransaction(accountDto.getLastTransaction());
        account.setType(AccountType.valueOf(account.getType().name()));
        account.setActive(accountDto.isActive());
        return account;
    }

    public static Account toAccount(AccountDetailedDto accountDetailedDto) {
        Account account = new Account();
        account.setId(accountDetailedDto.getId());
        account.setUser_name(accountDetailedDto.getUser_name());
        account.setEmail(accountDetailedDto.getEmail());
        account.setPhone(accountDetailedDto.getPhone());
        account.setCredit_number(accountDetailedDto.getCredit_number());
        account.setBalance(accountDetailedDto.getBalance());
        account.setCreatedAt(accountDetailedDto.getCreatedAt());
        account.setLastTransaction(accountDetailedDto.getLastTransaction());
        account.setType(AccountType.valueOf(account.getType().name()));
        account.setActive(accountDetailedDto.isActive());
        return account;
    }
}
