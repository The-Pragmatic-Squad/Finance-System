package io.github.thepragmaticsquad.fs.config;


import io.github.thepragmaticsquad.fs.dto.transaction.CreateTransactionDto;
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
        logger.info("Received Message From RabbitMQ: %1$s " + transaction);
        accountService.processTransaction(transaction);
    }
}
