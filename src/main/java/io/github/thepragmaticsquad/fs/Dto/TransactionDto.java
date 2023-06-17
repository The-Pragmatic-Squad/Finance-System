package io.github.thepragmaticsquad.fs.Dto;

import io.github.thepragmaticsquad.fs.Entity.Account;
import io.github.thepragmaticsquad.fs.Entity.AccountType;
import io.github.thepragmaticsquad.fs.Entity.TransactionStatus;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.sql.Date;

public class TransactionDto {
    private Long id;

    @NotNull(message = "Transaction type is required")
    private AccountType type ;

    @NotNull(message = "Amount is required")
    private Double amount ;

    @NotNull(message = "Balance before transaction is required")
    private Double balanceBefore;

    @NotNull(message = "Balance after transaction is required")
    private Double balanceAfter;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private Date date ;

    @NotBlank(message = "Details are required")
    private String details;
}
