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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String user_name;
    private String email;
    private String phone;
    private String credit_number ;
    private Double balance ;
    private Date createdAt;
    @Null
    private Date lastTransaction;
    @Enumerated(EnumType.STRING)
    private AccountType type ;
    private boolean isActive = true; 
}