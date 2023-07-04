package io.github.thepragmaticsquad.fs.repository;

import io.github.thepragmaticsquad.fs.entity.Account;
import io.github.thepragmaticsquad.fs.entity.Transaction;
import io.github.thepragmaticsquad.fs.enums.AccountType;
import io.github.thepragmaticsquad.fs.enums.TransactionStatus;
import io.github.thepragmaticsquad.fs.enums.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class TransactionsRepositoryTest {
    @Autowired
    TransactionsRepository transactionsRepository;
    @Test
    void testFindAllTransactionsByAccountId() {
        //given
        Long accountId = 1L;
        Transaction transaction1 = new Transaction(1L,new Account(
                accountId,"Ahmed_Kandel","test@gmail.com",
                "test password","01125974779",  "12154544522125" ,
                BigDecimal.valueOf(3000.0), LocalDateTime.now() , LocalDateTime.now(),
                AccountType.STANDARD, true),LocalDateTime.now(), TransactionType.DEPOSIT
        , TransactionStatus.SUCCESS,"description","details",BigDecimal.valueOf(50)
        ,BigDecimal.valueOf(100),BigDecimal.valueOf(150));

        Transaction transaction2 = new Transaction(2L,new Account(
                accountId,"Ahmed_Kandel","test@gmail.com",
                "test password","01125974779",  "12154544522125" ,
                BigDecimal.valueOf(3000.0), LocalDateTime.now() , LocalDateTime.now(),
                AccountType.STANDARD, true),LocalDateTime.now(), TransactionType.DEPOSIT
                , TransactionStatus.SUCCESS,"description","details",BigDecimal.valueOf(50)
                ,BigDecimal.valueOf(100),BigDecimal.valueOf(150));
        List<Transaction> transactionsToSave = List.of(transaction1, transaction2);
        // Save the transactions and get the saved entities
        List<Transaction> savedTransactions = transactionsRepository.saveAll(transactionsToSave);
        // When
        List<Transaction> transactionsUnderAccountId = transactionsRepository.findAllByAccountId(accountId);
        // Then
        assertThat(transactionsUnderAccountId).isNotNull();
        assertThat(transactionsUnderAccountId).containsAnyElementsOf(savedTransactions);
    }
}