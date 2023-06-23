package io.github.thepragmaticsquad.fs.dto.transaction;

import io.github.thepragmaticsquad.fs.dto.account.AccountAbstractedDto;
import io.github.thepragmaticsquad.fs.enums.TransactionStatus;
import io.github.thepragmaticsquad.fs.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailedDto {
    private Long id;

    @NotNull(message = "Account is required")
    private AccountAbstractedDto account;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDateTime date = LocalDateTime.now();

    @NotNull(message = "Transaction type is required")
    private TransactionType type;

    @NotNull(message = "Transaction status is required")
    private TransactionStatus status;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotNull(message = "Balance before transaction is required")
    private BigDecimal balanceBefore;

    @NotNull(message = "Balance after transaction is required")
    private BigDecimal balanceAfter;

}
