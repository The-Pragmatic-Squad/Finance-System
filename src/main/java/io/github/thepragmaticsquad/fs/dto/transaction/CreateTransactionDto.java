package io.github.thepragmaticsquad.fs.dto.transaction;

import io.github.thepragmaticsquad.fs.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionDto {

    private Long accountId;

    @NotNull(message = "Transaction type is required")
    private TransactionType type;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @Override
    public String toString() {
        return "A " + type + " transaction with amount " + amount + '$'+ " for account = " + accountId ;
    }
}
