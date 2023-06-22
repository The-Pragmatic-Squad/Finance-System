package io.github.thepragmaticsquad.fs.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.thepragmaticsquad.fs.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    private String phone;

    private String creditNumber;

    private BigDecimal balance;

    private LocalDateTime createdAt;

    private LocalDateTime lastTransaction;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private boolean active = true;
    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}