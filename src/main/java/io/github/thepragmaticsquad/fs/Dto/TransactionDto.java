package io.github.thepragmaticsquad.fs.dto;

import io.github.thepragmaticsquad.fs.entity.AccountType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

public class TransactionDto {
    private Long id;

    @NotNull(message = "account id is required")
    private AccountAbstractedDto account;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotNull(message = "Balance before transaction is required")
    private BigDecimal balanceBefore;

    @NotNull(message = "Balance after transaction is required")
    private BigDecimal balanceAfter;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDateTime date;

}
