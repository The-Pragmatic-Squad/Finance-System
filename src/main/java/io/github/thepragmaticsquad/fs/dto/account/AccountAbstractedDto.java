package io.github.thepragmaticsquad.fs.dto.account;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountAbstractedDto {
    private Long id;

    @NotBlank(message = "User name is required")
    private String username;

    @DecimalMin(value = "50", inclusive = false, message = "Balance must be greater than 0")
    private BigDecimal balance;

    @NotNull(message = "Created at date is required")
    private LocalDateTime createdAt;
}