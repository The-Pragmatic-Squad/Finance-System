package io.github.thepragmaticsquad.fs.config;


 import io.github.thepragmaticsquad.fs.dto.transaction.CreateTransactionDto;
 import io.github.thepragmaticsquad.fs.service.AccountService;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {
    Logger logger = LoggerFactory.getLogger(TransactionListener.class);
    private final AccountService accountService;

    public TransactionListener(AccountService accountService) {
        this.accountService = accountService;
    }

    @RabbitListener(queues = RabbitMQConfig.TRANSACTION_QUEUE)
    public void listener(SimpleTransactionDto transaction) {
        logger.info("Received Message From RabbitMQ: " + transaction);
        accountService.processTransaction(transaction);
    }
}
