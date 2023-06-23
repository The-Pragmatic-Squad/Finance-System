package io.github.thepragmaticsquad.fs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.thepragmaticsquad.fs.enums.TransactionStatus;
import io.github.thepragmaticsquad.fs.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Long id;

    @NotNull(message = "account id is required")
    @JsonIgnore
    private AccountAbstractedDto account;

    @NotNull
    private TransactionType type;

    @NotNull
    private TransactionStatus status;
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
