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
    @SequenceGenerator(name = "transaction_id_seq", sequenceName = "transaction_id_seq" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "transaction_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(name = "t_date")

    private Date date ;
    @Column(name = "t_type")
    @Enumerated(EnumType.STRING)
    private TransactionType type ;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status ;

    @NotNull(message = "Amount is required")
    private Double amount ;
    @Column(name = "balance_before")
    private Double balanceBefore;
    @Column(name = "balance_after")
    private Double balanceAfter;

    private String details;
}
