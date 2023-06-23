package io.github.thepragmaticsquad.fs.entity;

import io.github.thepragmaticsquad.fs.enums.TransactionStatus;
import io.github.thepragmaticsquad.fs.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @SequenceGenerator(name = "transaction_id_seq",
            sequenceName = "transaction_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "transaction_date")
    private LocalDateTime date;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private BigDecimal amount;

    @Column(name = "balance_before")
    private BigDecimal balanceBefore;

    @Column(name = "balance_after")
    private BigDecimal balanceAfter;
}
