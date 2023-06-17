package io.github.thepragmaticsquad.fs.Entity;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @SequenceGenerator(name = "accounts_id_seq", sequenceName = "accounts_id_seq" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "accounts_id_seq")
    private Long id ;
    private String user_name;
    private String email;
    private String phone;
    @Column(name = "created_at")
    private String credit_number ;
    private Double balance ;
    private Date createdAt;
    @Null
    private Date lastTransaction;
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType type ;
    @Column(name = "is_active")
    private boolean isActive = true; 
}