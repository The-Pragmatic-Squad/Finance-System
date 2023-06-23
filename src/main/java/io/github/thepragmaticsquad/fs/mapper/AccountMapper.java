package io.github.thepragmaticsquad.fs.mapper;

import io.github.thepragmaticsquad.fs.dto.account.AccountAbstractedDto;
import io.github.thepragmaticsquad.fs.dto.account.AccountDetailedDto;
import io.github.thepragmaticsquad.fs.dto.account.AccountDto;
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
    AccountDetailedDto toDetailedDto(Account account);

    @InheritInverseConfiguration
    Account toAccount(AccountDetailedDto account);

    AccountAbstractedDto toAbstractedDto(Account account);

    Account toAccount(AccountAbstractedDto accountDto);

    @Named("convertAccountTypeToString")
    default String convertAccountTypeToString(AccountType accountType) {
        return accountType != null ? accountType.name() : null;
    }

    @Named("convertToAccountType")
    default AccountType convertToAccountType(String accountTypeString) {
        return accountTypeString != null ? AccountType.valueOf(accountTypeString) : null;
    }

    @Mapping(target = "id", ignore = true)
    void updateAccountFromDto(AccountDetailedDto dto, @MappingTarget Account account);
}
