package sqssender.sqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.sqs.*;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import sqssender.config.SQSConfig;

public class SqsSender {

    private final Logger LOGGER = LoggerFactory.getLogger(SqsSender.class);
    private String QUEUE_URL = System.getenv("QUEUE_URL");
    private AmazonSQS sqs = new SQSConfig().amazonSQSAsyncClientBuilder();
    private SendMessageRequest sendMessageRequest = new SendMessageRequest();

    public void sendToQueue(String message){
        try{
            LOGGER.info("Message received: " + message);

            this.sendMessageRequest
                .withQueueUrl(QUEUE_URL)
                .withMessageBody(message);

            this.sqs.sendMessage(sendMessageRequest);
            LOGGER.info("Message sent to queue: " + message);
        }catch (Exception e){

            LOGGER.error("Error trying to send message: " + e.getMessage());
            new RuntimeException(e);
        }
    }
}
