package io.github.thepragmaticsquad.fs.config;


import io.github.thepragmaticsquad.fs.dto.transaction.CreateTransactionDto;
import io.github.thepragmaticsquad.fs.exception.account.AccountNotFoundException;
import io.github.thepragmaticsquad.fs.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {
    private final AccountService accountService;
    Logger logger = LoggerFactory.getLogger(TransactionListener.class);

    public TransactionListener(AccountService accountService) {
        this.accountService = accountService;
    }

    @RabbitListener(queues = RabbitMQConfig.TRANSACTION_QUEUE)
    public void listener(CreateTransactionDto transaction) {
        String logMessage = String.format("Received Transaction From The Gateway through RabbitMQ: %1$s", transaction);
        logger.info(logMessage);
        try {
            accountService.processTransaction(transaction);
        } catch (AccountNotFoundException e) {
            logMessage = String.format("we could not find the account with id %1$s, we cannot process the transaction", transaction.getAccountId());
            logger.error(logMessage);
        }

    }
}
