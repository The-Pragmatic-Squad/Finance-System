package io.github.thepragmaticsquad.fs.dto;

import io.github.thepragmaticsquad.fs.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String username;
    private BigDecimal balance;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime lastTransaction;
    private AccountType type;
    private boolean active;
}
