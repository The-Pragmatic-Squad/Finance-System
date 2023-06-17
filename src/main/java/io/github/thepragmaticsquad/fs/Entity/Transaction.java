package io.github.thepragmaticsquad.fs.Entity;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Account is required")
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private Date date ;

    @NotNull(message = "Transaction type is required")
    @ManyToOne
    @JoinColumn(name = "type_id")
    private AccountType type ;

    @NotNull(message = "Transaction status is required")
    @ManyToOne
    @JoinColumn(name = "status_id")
    private TransactionStatus status ;

    @NotNull(message = "Amount is required")
    private Double amount ;

    // I think that the (balanceBefore) and (balanceAfter) could be calculated on the fly  
    @NotNull(message = "Balance before transaction is required")
    private Double balanceBefore;

    @NotNull(message = "Balance after transaction is required")
    private Double balanceAfter;

    @NotBlank(message = "Details are required")
    private String details;
}
