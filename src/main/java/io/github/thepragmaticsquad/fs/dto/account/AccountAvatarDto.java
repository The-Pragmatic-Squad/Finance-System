package io.github.thepragmaticsquad.fs.dto.account;

import io.github.thepragmaticsquad.fs.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountAvatarDto {
    private Long id;

    private AccountType type;
    private String email;
    private Boolean active;
    private String username;
    private BigDecimal balance;

    private LocalDateTime createdAt;
}
