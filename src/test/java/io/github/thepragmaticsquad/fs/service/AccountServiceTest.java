package io.github.thepragmaticsquad.fs.service;

import io.github.thepragmaticsquad.fs.dto.account.*;
import io.github.thepragmaticsquad.fs.dto.transaction.*;
import io.github.thepragmaticsquad.fs.entity.*;
import io.github.thepragmaticsquad.fs.entity.creditcard.*;
import io.github.thepragmaticsquad.fs.enums.*;
import io.github.thepragmaticsquad.fs.exception.account.*;
import io.github.thepragmaticsquad.fs.mapper.*;
import io.github.thepragmaticsquad.fs.repository.*;
import io.github.thepragmaticsquad.fs.service.impl.*;
import org.junit.jupiter.api.*;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;

import java.math.*;
import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@ImportResource
class AccountServiceTest{
    @InjectMocks
    private AccountServicesImpl accountServices;
    @InjectMocks
    private TransactionServiceImpl transactionService;
    @InjectMocks
    private CardIssuer cardIssuer;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionsRepository transactionsRepository;
    @Mock
    private AccountMapper accountMapper;
//    @Mock
//     private TransactionMapper transactionMapper;
    @Spy
    private final TransactionMapper transactionMapper = Mappers.getMapper(TransactionMapper.class);
    Account account = new Account(
            1L,"khaled_badr","test@gmail.com",
            "test password","01125974779",  "12154544522125" ,
            BigDecimal.valueOf(3000.0), LocalDateTime.now() , LocalDateTime.now(),
            AccountType.STANDARD, true);

    CreateAccountDto createAccountDto = new CreateAccountDto(
            1L,"khaled_badr","test@gmail.com",
            "test password","01125974779",
            AccountType.STANDARD, BigDecimal.valueOf(3000.0)
    );
    UpdateAccountDto updateAccountDto = new UpdateAccountDto(
            account.getUsername(),
            account.getPassword(),
            account.getEmail(),
            "01100899074",
            account.getType()
    );
    CreateTransactionDto transactionDto = new CreateTransactionDto(
            1L,TransactionType.DEPOSIT , BigDecimal.valueOf(200));
    TransactionDetailsDto transactionDetailsDto = new TransactionDetailsDto(1L
            , new AccountAvatarDto(1L,AccountType.STANDARD,"test@gmail.com",true,"user test"
            , BigDecimal.valueOf(100), LocalDateTime.now()),LocalDateTime.now(), TransactionType.DEPOSIT
            , TransactionStatus.SUCCESS
            , BigDecimal.valueOf(50),BigDecimal.valueOf(100)
            , BigDecimal.valueOf(150));

    @Test
    void addAccountSuccess(){
        Mockito.when(accountRepository.save(account))
                .thenReturn(account);
        Mockito.when(cardIssuer.issueCreditCard())
                .thenReturn("1234-4321-5875-9442");

        assertEquals(accountServices.createAccount(createAccountDto) ,
                createAccountDto.getId());
    }
    @Test
    void addAccountFailed(){
        Mockito.when(accountRepository.findAccountByEmailOrUsername(
                createAccountDto.getEmail() , createAccountDto.getUsername()))
                .thenReturn(Optional.of(account));

        AccountAlreadyExistException exception = Assertions.assertThrows(AccountAlreadyExistException.class,
                ()->{
                    accountServices.createAccount(createAccountDto);
                } , "Account already exist");
        Assertions.assertEquals("Account already exist" , exception.getMessage());
    }
    @Test
    void getAllAccounts(){
        Mockito.when(accountRepository.findAll())
                .thenReturn(List.of(account));
        assertEquals(1,accountServices.getAllAccounts().size());
    }
    @Test
    void getAccountSuccess(){
        Mockito.when(accountRepository.findAccountByIdAndActiveTrue(1L))
                .thenReturn(Optional.of(account));

        assertEquals(account.getUsername(),accountServices.getAccount(1L).getUsername());
    }
    @Test
    void getAccountFailed(){
        Mockito.when(accountRepository.findAccountByIdAndActiveTrue(3L))
                .thenReturn(Optional.empty());

        AccountNotFoundException exception = Assertions.assertThrows(AccountNotFoundException.class,
                ()->{
            accountServices.getAccount(3L);
                } , "Account not found");

        assertEquals("Account not found" , exception.getMessage());
    }
    @Test
    void getAccountAvatarSuccess(){
        Mockito.when(accountRepository.findAccountByIdAndActiveTrue(account.getId()))
                .thenReturn(Optional.of(account));
        assertEquals(account.getUsername() , accountServices.getAccountAvatar(account.getId()).getUsername());
    }
    @Test
    void getAccountAvatarFailed(){
        Mockito.when(accountRepository.findAccountByIdAndActiveTrue(3L))
                .thenReturn(Optional.empty());
        AccountNotFoundException exception = Assertions.assertThrows(AccountNotFoundException.class,
                ()->{
            accountServices.getAccountAvatar(3L);
                },"Account not found");
        assertEquals("Account not found" , exception.getMessage());
    }


//    @Test
//    void updateAccountSuccess(){
//        Mockito.when(accountRepository.findAccountByIdAndActiveTrue(1L))
//                .thenReturn(Optional.of(account));
//
//
//        assertEquals("01100899074" ,
//                accountServices.updateAccount(1L , updateAccountDto));
//    }
    @Test
    void updateAccountFailed(){

        Mockito.when(accountRepository.findAccountByIdAndActiveTrue(3L))
                .thenReturn(Optional.empty());
        AccountNotFoundException exception = Assertions.assertThrows(AccountNotFoundException.class,
                ()->{
                    accountServices.updateAccount(3L , new UpdateAccountDto() );
                },"Account not found");
        assertEquals("Account not found" , exception.getMessage());
    }
    @Test
    void deleteAccountSuccess(){
        Mockito.when(accountRepository.findAccountByIdAndActiveTrue(account.getId()))
                .thenReturn(Optional.of(account));
        accountServices.deleteAccount(account.getId());

        assertFalse(account.isActive());
    }
    @Test
    void deleteAccountFailed(){
        Mockito.when(accountRepository.findAccountByIdAndActiveTrue(3L))
                .thenReturn(Optional.empty());

        AccountNotFoundException exception = Assertions.assertThrows(AccountNotFoundException.class,
                ()->{
                    accountServices.deleteAccount(3L);
                } , "Account not found");

        assertEquals("Account not found" , exception.getMessage());
    }
    @Test
    void processTransactionTestAccountFound(){
        // Mock data
        // Make sure to initialize the account object
//        Account account = new Account();
//        account.setId(1L);
//        account.setLastTransaction(LocalDateTime.now());
//        account.setBalance(BigDecimal.valueOf(100));
//        account.setActive(true);
//
//        TransactionDetailsDto transactionDetailsDto = new TransactionDetailsDto();
//        transactionDetailsDto.setId(1L);
//        transactionDetailsDto.setDate(LocalDateTime.now());
//        transactionDetailsDto.setBalanceAfter(BigDecimal.valueOf(50));

        // Configure mock behavior for accountRepository
        Mockito.when(accountRepository.findAccountByIdIs(1L)).thenReturn(Optional.of(account));

        // Perform the test (assuming transactionDto is properly initialized)
        TransactionDetailsDto result = accountServices.processTransaction(transactionDto);

        // Verify repository interaction
        verify(accountRepository).save(account);

        // Verify the updated account fields
        assertThat(account.getLastTransaction()).isEqualTo(LocalDateTime.now()); // Verify that lastTransaction is updated
        assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(50)); // Verify that the balance is updated
        assertThat(account.isActive()).isTrue(); // Verify that isActive is updated

        // Verify the returned transaction details
        assertThat(result).isEqualTo(transactionDetailsDto);
    }
    @Test
    void processTransactionTestAccountNotFound(){
        CreateTransactionDto transactionDtoBadId = transactionDto;
        transactionDtoBadId.setAccountId(3L);

        Mockito.when(accountRepository.findAccountByIdIs(3L))
                .thenReturn(Optional.empty());
        AccountNotFoundException exception = Assertions.assertThrows(AccountNotFoundException.class,
                ()->{
                    accountServices.processTransaction(transactionDtoBadId);
                }, "Account not found");

        assertEquals("Account not found" , exception.getMessage());
    }
    @Test
    void getTransactionsByAccountId_whenAccountFound_thenReturnAllTransactions(){
        // Mock data
        Long accountId = 1L;
        Transaction transaction = transactionMapper.toTransaction(transactionDetailsDto);
        Mockito.when(transactionsRepository.findAllByAccountId(accountId))
                .thenReturn(List.of(transaction));
        // Perform the test
        List<TransactionDetailsDto> result = transactionService.getTransactionsByAccountId(accountId);
        // Verify the result
        assertThat(result).isNotNull().hasSize(1);
        // Verify repository interaction
        verify(transactionsRepository).findAllByAccountId(accountId);
    }
    @Test
    void getTransactionsByAccountId_whenAccountNotFound_thenFailedGetAllTransactions(){
        Mockito.when(accountRepository.findById(3L)).thenReturn(Optional.empty());
        AccountNotFoundException exception = Assertions.assertThrows(AccountNotFoundException.class,
                ()->{
                    accountServices.getAccount(3L);
                } , "Account not found");

        assertEquals("Account not found" , exception.getMessage());

    }
}
