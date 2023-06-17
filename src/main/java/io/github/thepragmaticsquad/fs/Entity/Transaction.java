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

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private Date date ;

    @Enumerated(EnumType.STRING)
    private TransactionType type ;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status ;

    @NotNull(message = "Amount is required")
    private Double amount ;

    private Double balanceBefore;

    private Double balanceAfter;

    private String details;

}
