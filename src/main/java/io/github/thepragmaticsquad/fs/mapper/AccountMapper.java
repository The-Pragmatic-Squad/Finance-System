package io.github.thepragmaticsquad.fs.mapper;

import io.github.thepragmaticsquad.fs.dto.account.AccountAvatarDto;
import io.github.thepragmaticsquad.fs.dto.account.AccountDto;
import io.github.thepragmaticsquad.fs.dto.account.CreateAccountDto;
import io.github.thepragmaticsquad.fs.dto.account.UpdateAccountDto;
import io.github.thepragmaticsquad.fs.entity.Account;
import io.github.thepragmaticsquad.fs.enums.AccountType;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "type", target = "type", qualifiedByName = "convertToAccountType")
    AccountDto toAccountDto(Account account);

    @InheritInverseConfiguration
    Account toAccount(AccountDto accountDto);

    @Mapping(source = "type", target = "type", qualifiedByName = "convertToAccountType")
    CreateAccountDto toDetailedDto(Account account);

    @InheritInverseConfiguration
    Account toAccount(CreateAccountDto account);

    AccountAvatarDto toAbstractedDto(Account account);

    Account toAccount(AccountAvatarDto accountDto);

    @Named("convertAccountTypeToString")
    default String convertAccountTypeToString(AccountType accountType) {
        return accountType != null ? accountType.name() : null;
    }

    @Named("convertToAccountType")
    default AccountType convertToAccountType(String accountTypeString) {
        return accountTypeString != null ? AccountType.valueOf(accountTypeString) : null;
    }

    @Mapping(target = "id", ignore = true)

    void updateAccountFromDto(@MappingTarget Account account, UpdateAccountDto dto);

    @BeforeMapping
    default void updateNotNullableFields(@MappingTarget Account account, UpdateAccountDto dto) {
        if (dto.getUsername() == null) {
            dto.setUsername(account.getUsername());
        }
        if (dto.getType() == null) {
            dto.setType(account.getType());
        }
        if (dto.getPassword() == null) {
            dto.setPassword(account.getPassword());
        }
        if (dto.getPhone() == null) {
            dto.setPhone(account.getPhone());
        }
        if (dto.getEmail() == null) {
            dto.setEmail(account.getEmail());
        }
    }
}
