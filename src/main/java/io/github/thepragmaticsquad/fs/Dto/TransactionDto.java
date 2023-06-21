package io.github.thepragmaticsquad.fs.dto;

import io.github.thepragmaticsquad.fs.enums.TransactionType;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionDto {
    private Long id;

    @NotNull(message = "account id is required")
    private AccountAbstractedDto account;

    @NotNull
    private TransactionType transactionType;

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
