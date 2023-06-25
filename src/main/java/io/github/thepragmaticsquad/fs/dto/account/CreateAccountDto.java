package io.github.thepragmaticsquad.fs.dto.account;

import io.github.thepragmaticsquad.fs.enums.AccountType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountDto {
    private Long id;

    @NotBlank(message = "User name is required")
    @Length(min = 8, max = 20, message = "Username must be between 8 and 20 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Length(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Pattern(regexp = "^[0-9]{11}$", message = "Phone number must be 11 digits")
    private String phone;

    private AccountType type = AccountType.STANDARD;
    @Min(value = 0, message = "Balance must be greater than 0")
    private BigDecimal balance = BigDecimal.valueOf(0.0);

}
