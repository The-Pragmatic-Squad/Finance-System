package io.github.thepragmaticsquad.fs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.thepragmaticsquad.fs.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.active = true")
    List<Account> findAllActiveAccounts();

    Boolean getAccountByIdAndActiveTrue(Long id);


}