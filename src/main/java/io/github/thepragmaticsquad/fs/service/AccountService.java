package io.github.thepragmaticsquad.fs.service;

import io.github.thepragmaticsquad.fs.dto.account.AccountAvatarDto;
import io.github.thepragmaticsquad.fs.dto.account.AccountDto;
import io.github.thepragmaticsquad.fs.dto.account.CreateAccountDto;
import io.github.thepragmaticsquad.fs.dto.account.UpdateAccountDto;
import io.github.thepragmaticsquad.fs.dto.transaction.CreateTransactionDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {

    Long createAccount(CreateAccountDto account);

    List<AccountDto> getAllAccounts();

    AccountDto getAccount(Long id);

    AccountAvatarDto getAccountAvatar(Long id);

    AccountDto updateAccount(Long id, UpdateAccountDto account);

    void deleteAccount(Long id);

    Page<TransactionDetailsDto> getTransactionsByAccountId(Long id, int page, int size);


    TransactionDetailsDto processTransaction(CreateTransactionDto transactionDto);
}
