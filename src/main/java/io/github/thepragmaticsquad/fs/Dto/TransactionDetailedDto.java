package io.github.thepragmaticsquad.fs.dto;

import io.github.thepragmaticsquad.fs.entity.AccountType;
import io.github.thepragmaticsquad.fs.entity.TransactionStatus;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDetailedDto {
    private Long id;

    @NotNull(message = "Account is required")
    private AccountAbstractedDto account;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDateTime date;

    @NotNull(message = "Transaction type is required")
    private AccountType type;

    @NotNull(message = "Transaction status is required")
    private TransactionStatus status;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotNull(message = "Balance before transaction is required")
    private BigDecimal balanceBefore;

    @NotNull(message = "Balance after transaction is required")
    private BigDecimal balanceAfter;

}
