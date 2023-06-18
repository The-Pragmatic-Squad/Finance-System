package io.github.thepragmaticsquad.fs.mapper;

import io.github.thepragmaticsquad.fs.dto.*;
import io.github.thepragmaticsquad.fs.entity.Account;
import io.github.thepragmaticsquad.fs.entity.AccountType;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    public static final AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    @Named("convertAccountTypeToString")
    default String convertAccountTypeToString(AccountType accountType) {
        return accountType != null ? accountType.name() : null;
    }

    @Named("convertToAccountType")
    default AccountType convertToAccountType(String accountTypeString) {
        return accountTypeString != null ? AccountType.valueOf(accountTypeString) : null;
    }

    @Mapping(source = "type", target = "type", qualifiedByName = "convertAccountTypeToString")
    AccountDto toDto(Account account);

    AccountAbstractedDto toAbstractedDto(Account account);

    @Mapping(source = "type", target = "type", qualifiedByName = "convertAccountTypeToString")
    AccountDetailedDto toDetailedDto(Account account);

    Account toAccount(AccountAbstractedDto accountAbstractedDto);


}
