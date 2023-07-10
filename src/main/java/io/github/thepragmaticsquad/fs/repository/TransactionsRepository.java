package io.github.thepragmaticsquad.fs.repository;

import io.github.thepragmaticsquad.fs.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.account.id = ?1")
    Page<Transaction> findAllByAccountId(Long accountId, Pageable pageable);

    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.account.id = ?1")
    Long countAllByAccountId(Long accountId);
}
