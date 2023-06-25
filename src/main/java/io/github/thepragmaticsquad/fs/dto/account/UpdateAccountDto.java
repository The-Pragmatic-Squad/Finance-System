package io.github.thepragmaticsquad.fs.dto.account;

import io.github.thepragmaticsquad.fs.enums.AccountType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountDto {
    private String username;

    @Length(min = 8)
    private String password;

    @Email
    private String email;

    @Pattern(regexp = "^[0-9]{11}$")
    private String phone;

    private AccountType type;
}
