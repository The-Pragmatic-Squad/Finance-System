package io.github.thepragmaticsquad.fs.mapper;

import io.github.thepragmaticsquad.fs.dto.account.AccountAvatarDto;
import io.github.thepragmaticsquad.fs.dto.transaction.CreateTransactionDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailsDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDto;
import io.github.thepragmaticsquad.fs.entity.Account;
import io.github.thepragmaticsquad.fs.entity.Transaction;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "account", target = "account", qualifiedByName = "toAvatarDto")
    TransactionDto toDto(Transaction transaction);

    @Mapping(source = "account", target = "account", qualifiedByName = "toAvatarDto")
    TransactionDetailsDto toDetailsDto(Transaction transaction);


    @Mapping(source = "account", target = "account")
    Transaction toTransaction(TransactionDetailsDto dto);

    @Mapping(source = "accountId", target = "account.id")
    Transaction toTransaction(CreateTransactionDto dto);

    @Named("toAvatarDto")
    default AccountAvatarDto toAvatarDto(Account account) {
        return AccountMapper.INSTANCE.toAvatarDto(account);
    }

}