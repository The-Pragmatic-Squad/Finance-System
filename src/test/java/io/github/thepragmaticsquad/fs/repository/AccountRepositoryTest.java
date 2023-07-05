package io.github.thepragmaticsquad.fs.repository;

import io.github.thepragmaticsquad.fs.entity.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void createAccount() {
        Account account = new Account();
        account.setActive(true);
        account.setBalance(BigDecimal.valueOf(1000));
        account.setCreditNumber("1234-5678-9012-3456");
        account.setEmail("aaa@abc.com");
        account.setPassword("abcdefghjkl");
        account.setPhone("01234567890");

        // act on the repository
        account = accountRepository.save(account);

        // Assert that the account was saved
        assertNotNull(account.getId());

    }

    @Test
    void testFindAccountsByActiveTrue() {
        // Create some test accounts
        Account activeAccount1 = new Account();
        activeAccount1.setActive(true);
        activeAccount1.setBalance(BigDecimal.valueOf(1000));
        activeAccount1.setCreditNumber("1234-5678-9012-3456");
        activeAccount1.setEmail("abc@abc.com");
        activeAccount1.setPassword("abcdefghjkl");
        activeAccount1.setPhone("01234567890");

        accountRepository.save(activeAccount1);

        Account activeAccount2 = new Account();
        activeAccount2.setActive(true);
        accountRepository.save(activeAccount2);

        Account inactiveAccount = new Account();
        inactiveAccount.setActive(false);
        accountRepository.save(inactiveAccount);

        // Call the repository method
        List<Account> activeAccounts = accountRepository.findAccountsByActiveTrue();

        // Assert that only active accounts are returned
        assertEquals(2, activeAccounts.size());
        assertTrue(activeAccounts.contains(activeAccount1));
        assertTrue(activeAccounts.contains(activeAccount2));
        assertFalse(activeAccounts.contains(inactiveAccount));
    }

    @Test
    void testFindAccountByIdAndActiveTrue() {
        // Create a test account
        Account activeAccount = new Account();
        activeAccount.setActive(true);
        accountRepository.save(activeAccount);

        // Call the repository method with the account's ID
        Optional<Account> foundAccount = accountRepository.findAccountByIdAndActiveTrue(activeAccount.getId());

        // Assert that the active account is found
        assertTrue(foundAccount.isPresent());
        assertEquals(activeAccount.getId(), foundAccount.get().getId());
    }

    @Test
    void testFindAccountByEmailOrUsername() {
        // Create a test account
        Account account = new Account();
        account.setEmail("test@example.com");
        account.setUsername("testuser");
        accountRepository.save(account);

        // Call the repository method with the email and username
        Optional<Account> foundAccountByEmail = accountRepository.findAccountByEmailOrUsername("test@example.com", "invalid");
        Optional<Account> foundAccountByUsername = accountRepository.findAccountByEmailOrUsername("invalid", "testuser");

        // Assert that the account is found by email or username
        assertTrue(foundAccountByEmail.isPresent());
        assertTrue(foundAccountByUsername.isPresent());
        assertEquals(account.getId(), foundAccountByEmail.get().getId());
        assertEquals(account.getId(), foundAccountByUsername.get().getId());
    }
}
