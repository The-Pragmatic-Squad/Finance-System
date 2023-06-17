package io.github.thepragmaticsquad.fs.Dto;

import io.github.thepragmaticsquad.fs.Entity.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Date;

@Data
public class AccountDetailedDto {

    private Long id ;
    @NotBlank(message = "User name is required")
    private String user_name;
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @NotBlank(message = "Phone number is required")
    private String phone;
    @Pattern(regexp = "\\d{16}", message = "Credit card number must be 16 digits")
    @NotBlank(message = "Credit card number is required")
    private String credit_number ;
    @NotNull(message = "Balance is required")
    @DecimalMin(value = "50", inclusive = false, message = "Balance must be greater than 0")
    private Double balance ;
    @NotNull(message = "Created at date is required")
    private Date createdAt;
    @Null
    private Date lastTransaction;
    @NotNull(message = "Account type is required")
    private AccountType type ;
    private boolean isActive = true;
}
