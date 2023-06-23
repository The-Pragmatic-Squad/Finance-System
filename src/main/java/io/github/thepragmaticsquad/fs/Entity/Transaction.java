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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private BigDecimal amount;

    private BigDecimal balanceBefore;

    private BigDecimal balanceAfter;
}
