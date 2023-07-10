package io.github.thepragmaticsquad.fs.mapper;

import io.github.thepragmaticsquad.fs.dto.account.AccountAvatarDto;
import io.github.thepragmaticsquad.fs.dto.account.AccountDto;
import io.github.thepragmaticsquad.fs.dto.account.CreateAccountDto;
import io.github.thepragmaticsquad.fs.dto.account.UpdateAccountDto;
import io.github.thepragmaticsquad.fs.entity.Account;
import io.github.thepragmaticsquad.fs.enums.AccountType;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "type", target = "type", qualifiedByName = "convertToAccountType")
    AccountDto toAccountDto(Account account);

    AccountAvatarDto toAvatarDto(Account account);


    @InheritInverseConfiguration
    Account toAccount(CreateAccountDto account);

    Account toAccount(AccountAvatarDto accountDto);


    @Named("convertToAccountType")
    default AccountType convertToAccountType(String accountTypeString) {
        return accountTypeString != null ? AccountType.valueOf(accountTypeString) : null;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target ="type", ignore = true)
    void updateAccountFromDto(@MappingTarget Account account, UpdateAccountDto dto);

}
