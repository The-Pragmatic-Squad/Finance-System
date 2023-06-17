package io.github.thepragmaticsquad.fs.Dto;

import io.github.thepragmaticsquad.fs.Entity.AccountType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Data
public class AccountDto {
    private Long id ;

    @NotBlank(message = "User name is required")
    private String user_name;


    @NotNull(message = "Balance is required")
    @DecimalMin(value = "50", inclusive = false, message = "Balance must be greater than 0")
    private Double balance ;

    @NotNull(message = "Created at date is required")
    private Date createdAt;
    @Null
    private Date lastTransaction;
    @NotNull(message = "Account type is required")
    @ManyToOne
    @JoinColumn(name = "type_id")
    private AccountType type ;
    private boolean isActive = true;
}