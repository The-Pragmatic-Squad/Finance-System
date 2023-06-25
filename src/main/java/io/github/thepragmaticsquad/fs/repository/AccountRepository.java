package io.github.thepragmaticsquad.fs.repository;

import io.github.thepragmaticsquad.fs.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAccountsByActiveTrue();

    Optional<Account> findAccountByIdAndActiveTrue(Long id);
    @Query("SELECT a FROM Account a WHERE a.email = ?1 OR a.username = ?2")
    Optional<Account> findAccountByEmailOrUsername(String email,String username);

    Optional<Account> findAccountByIdIs(Long id);
}