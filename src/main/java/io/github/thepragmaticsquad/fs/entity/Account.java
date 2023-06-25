package io.github.thepragmaticsquad.fs.entity;

import io.github.thepragmaticsquad.fs.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.mindrot.jbcrypt.BCrypt;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class Account {
    @Id
    @SequenceGenerator(
            name = "accounts_id_seq",
            sequenceName = "accounts_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounts_id_seq")
    private Long id;

    private String username;

    private String email;

    private String password;

    private String phone;

    @Column(name = "credit_number")
    private String creditNumber;

    @Column(name = "balance", columnDefinition = "numeric")
    private BigDecimal balance;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_transaction")
    private LocalDateTime lastTransaction;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType type;

    private boolean active;


    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}