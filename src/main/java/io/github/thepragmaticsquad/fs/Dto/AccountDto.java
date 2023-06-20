package io.github.thepragmaticsquad.fs.dto;

import io.github.thepragmaticsquad.fs.enums.AccountType;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDto {
    private Long id;

    @NotBlank(message = "User name is required")
    private String username;

    @NotNull(message = "Balance is required")
    @DecimalMin(value = "50", inclusive = false, message = "Balance must be greater than 50")
    private BigDecimal balance;

    @NotNull(message = "Created at date is required")
    private LocalDateTime createdAt;

    @Null
    private LocalDateTime lastTransaction;

    @NotNull(message = "Account type is required")
    private AccountType type;

    private boolean active = true;
}
