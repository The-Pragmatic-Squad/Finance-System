package io.github.thepragmaticsquad.fs.mapper;

import io.github.thepragmaticsquad.fs.dto.account.AccountAvatarDto;
import io.github.thepragmaticsquad.fs.dto.transaction.CreateTransactionDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDetailsDto;
import io.github.thepragmaticsquad.fs.dto.transaction.TransactionDto;
import io.github.thepragmaticsquad.fs.entity.Account;
import io.github.thepragmaticsquad.fs.entity.Transaction;
import io.github.thepragmaticsquad.fs.enums.TransactionStatus;
import io.github.thepragmaticsquad.fs.enums.TransactionType;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "account", target = "account", qualifiedByName = "toAbstractedDto")
    TransactionDto toDto(Transaction transaction);

    @Mapping(source = "account", target = "account", qualifiedByName = "toAbstractedDto")
    TransactionDetailsDto toDetailsDto(Transaction transaction);


    @Mapping(source = "account", target = "account")
    Transaction toTransaction(TransactionDetailsDto dto);

    @Mapping(source = "accountId", target = "account.id")
    Transaction toTransaction(CreateTransactionDto dto);

    @Named("toAbstractedDto")
    default AccountAvatarDto toAbstractedDto(Account account) {
        return AccountMapper.INSTANCE.toAbstractedDto(account);
    }

    @Named("toAccount")
    default Account toAccount(AccountAvatarDto accountDto) {
        return AccountMapper.INSTANCE.toAccount(accountDto);
    }

    @Named("convertTransactionTypeToString")
    default String convertTransactionTypeToString(TransactionType type) {
        return type != null ? type.name() : null;
    }

    @Named("convertToTransactionType")
    default TransactionType convertToTransactionType(String typeString) {
        return typeString != null ? TransactionType.valueOf(typeString) : null;
    }

    @Named("convertTransactionStatusToString")
    default String convertTransactionStatusToString(TransactionStatus status) {
        return status != null ? status.name() : null;
    }

    @Named("convertToTransactionStatus")
    default TransactionStatus convertToTransactionStatus(String statusString) {
        return statusString != null ? TransactionStatus.valueOf(statusString) : null;
    }


}