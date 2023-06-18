package io.github.thepragmaticsquad.fs.entity;

import java.math.BigDecimal;
import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

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
    private Date createdAt;
    private Date lastTransaction;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Column(name = "active",columnDefinition = "boolean default true")
    private boolean active;
}