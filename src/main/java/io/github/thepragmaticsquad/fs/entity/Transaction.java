package io.github.thepragmaticsquad.fs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @SequenceGenerator(name = "transactions_id_seq",
            sequenceName = "transactions_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @Column(name = "transaction_date")
    private LocalDateTime date;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private String description;

    private String details;
    @Column(columnDefinition = "numeric")
    private BigDecimal amount;

    @Column(name = "balance_before", columnDefinition = "numeric")
    private BigDecimal balanceBefore;

    @Column(name = "balance_after", columnDefinition = "numeric")
    private BigDecimal balanceAfter;

}
