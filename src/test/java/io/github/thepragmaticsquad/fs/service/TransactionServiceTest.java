package io.github.thepragmaticsquad.fs.service;

import io.github.thepragmaticsquad.fs.dto.account.AccountAvatarDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailsDto;
import io.github.thepragmaticsquad.fs.entity.Transaction;
import io.github.thepragmaticsquad.fs.enums.AccountType;
import io.github.thepragmaticsquad.fs.enums.TransactionStatus;
import io.github.thepragmaticsquad.fs.enums.TransactionType;
import io.github.thepragmaticsquad.fs.exception.account.AccountNotFoundException;
import io.github.thepragmaticsquad.fs.exception.transaction.TransactionNotFoundException;
import io.github.thepragmaticsquad.fs.mapper.TransactionMapper;
import io.github.thepragmaticsquad.fs.repository.AccountRepository;
import io.github.thepragmaticsquad.fs.repository.TransactionsRepository;
import io.github.thepragmaticsquad.fs.service.impl.AccountServicesImpl;
import io.github.thepragmaticsquad.fs.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
class TransactionServiceTest {
    @Mock
    private TransactionsRepository transactionsRepository;
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private TransactionServiceImpl transactionService;
    @InjectMocks
    private AccountServicesImpl accountServices;
    @Spy
    private final TransactionMapper transactionMapper = Mappers.getMapper(TransactionMapper.class);
    TransactionDetailsDto transactionDetailsDto = new TransactionDetailsDto(1L
            , new AccountAvatarDto(1L,AccountType.STANDARD,"test@gmail.com",true,"user test"
            , BigDecimal.valueOf(100), LocalDateTime.now()),LocalDateTime.now(), TransactionType.DEPOSIT
            , TransactionStatus.SUCCESS
            , BigDecimal.valueOf(50),BigDecimal.valueOf(100)
            , BigDecimal.valueOf(150));
    @Test
    void getTransactionByIdSuccess() {
        Transaction transaction = transactionMapper.toTransaction(transactionDetailsDto);
        Mockito.when(transactionsRepository.findById(1L))
                .thenReturn(Optional.of(transaction));
        TransactionDetailsDto actualTransactionDetailsDto = transactionService.getTransactionById(1L);
        assertNotNull(actualTransactionDetailsDto);
        assertEquals(1L,actualTransactionDetailsDto.getId());
    }
    @Test
    void getTransactionByIdFailed() {
        Mockito.when(transactionsRepository.findById(3L))
                .thenReturn(Optional.empty());
        TransactionNotFoundException exception = Assertions.assertThrows(TransactionNotFoundException.class,
                ()->{
                    transactionService.getTransactionById(3L);
                } , "Transaction not found");
        assertEquals("Transaction not found" , exception.getMessage());
    }
    @Test
    void getAllTransactions() {
        Transaction transaction = transactionMapper.toTransaction(transactionDetailsDto);
        Mockito.when(transactionsRepository.findAll())
                .thenReturn(List.of(transaction));
        List<TransactionDetailsDto> transactionDetailsDtoList = transactionService.getAllTransactions();
        // Assert that the returned list is not null
        assertThat(transactionDetailsDtoList).isNotNull().hasSize(1);
        // Assert that the first element in the list matches the mocked transaction
        assertThat(transactionDetailsDtoList.get(0)).usingRecursiveComparison().isEqualTo(transaction);
    }

    @Test
    void getTransactionsByAccountId() {
        Long accountId = 1L;
        Transaction transaction = transactionMapper.toTransaction(transactionDetailsDto);
        Mockito.when(transactionsRepository.findAllByAccountId(accountId))
                .thenReturn(List.of(transaction));
        List<TransactionDetailsDto> transactionDetailsDtoList = transactionService.getTransactionsByAccountId(accountId);
        // Assert that the returned list is not null
        assertThat(transactionDetailsDtoList).isNotNull().hasSize(1);
        // Assert that the first element in the list matches the mocked transaction
        assertThat(transactionDetailsDtoList.get(0)).usingRecursiveComparison().isEqualTo(transaction);
    }
    @Test
    void getTransactionsByAccountIdNotFound() {
        Mockito.when(accountRepository.findAccountByIdAndActiveTrue(3L))
                .thenReturn(Optional.empty());
        AccountNotFoundException exception = Assertions.assertThrows(AccountNotFoundException.class,
                ()->{
                    accountServices.getAccount(3L);
                } , "Account not found");
        assertEquals("Account not found" , exception.getMessage());
    }

    @Test
    void processTransaction() {

    }
}