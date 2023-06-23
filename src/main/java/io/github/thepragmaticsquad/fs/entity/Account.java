package io.github.thepragmaticsquad.fs.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.thepragmaticsquad.fs.enums.AccountType;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @SequenceGenerator(
            name = "accounts_id_seq",
            sequenceName = "accounts_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounts_id_seq")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column (name = "phone")
    private String phone;

    @Column(name = "credit_number")
    private String creditNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_transaction")
    private LocalDateTime lastTransaction;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType type = AccountType.STANDARD;

    @Column(name = "active")
    private boolean active = true;
}