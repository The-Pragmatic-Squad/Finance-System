package io.github.thepragmaticsquad.fs.service;

import io.github.thepragmaticsquad.fs.dto.account.AccountAvatarDto;
import io.github.thepragmaticsquad.fs.dto.account.CreateAccountDto;
import io.github.thepragmaticsquad.fs.dto.account.AccountDto;
import io.github.thepragmaticsquad.fs.dto.account.UpdateAccountDto;
import io.github.thepragmaticsquad.fs.dto.transaction.CreateTransactionDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailsDto;



import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccounts();

    Long createAccount(CreateAccountDto account);

    AccountDto getAccount(Long id);

    AccountAvatarDto getAccountAvatar(Long id);

    AccountDto updateAccount(Long id, UpdateAccountDto account);

    void deleteAccount(Long id);

    void processTransaction(SimpleTransactionDto transactionDto);

    List<TransactionDetailsDto> getTransactionsByAccountId(Long id);
}
