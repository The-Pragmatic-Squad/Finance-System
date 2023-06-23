package io.github.thepragmaticsquad.fs.dto;

import io.github.thepragmaticsquad.fs.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailedDto {

    private Long id;
    @NotBlank(message = "User name is required")
    private String username;
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is reqired")
    private String password;
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @NotBlank(message = "Phone number is required")
    private String phone;
    @Pattern(regexp = "\\d{16}", message = "Credit card number must be 16 digits")
    @NotBlank(message = "Credit card number is required")
    private String creditNumber;
    @NotNull(message = "Balance is required")
    @DecimalMin(value = "50", inclusive = false, message = "Balance must be greater than 0")
    private BigDecimal balance;
    @NotNull(message = "Created at date is required")
    private LocalDateTime createdAt;
    @Null
    private LocalDateTime lastTransaction;
    @NotNull(message = "Account type is required")
    private AccountType type = AccountType.STANDARD;
    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean active = true;

}