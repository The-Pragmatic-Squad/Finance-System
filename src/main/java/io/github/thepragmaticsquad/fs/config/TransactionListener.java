package io.github.thepragmaticsquad.fs.config;

 import io.github.thepragmaticsquad.fs.dto.transaction.SimpleTransactionModel;
 import io.github.thepragmaticsquad.fs.service.TransactionService;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {
    Logger logger = LoggerFactory.getLogger(TransactionListener.class);
    private final TransactionService transactionService;

    public TransactionListener(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RabbitListener(queues = RabbitMQConfig.TRANSACTION_QUEUE)
    public void listener(SimpleTransactionModel transaction) {
        logger.info("Received Message From RabbitMQ: " + transaction);
        System.out.println("Received Message From RabbitMQ: " + transaction);

        transactionService.processTransaction(transaction);
    }
}
