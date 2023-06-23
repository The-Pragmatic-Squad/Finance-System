package io.github.thepragmaticsquad.fs.repository;

import io.github.thepragmaticsquad.fs.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByAccount_Id(Long accountId);
}