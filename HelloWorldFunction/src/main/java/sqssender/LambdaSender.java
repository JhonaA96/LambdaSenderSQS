package sqssender;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.slf4j.*;

import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;

/**
 * Handler for requests to Lambda function.
 */
public class LambdaSender implements RequestHandler<Map<String, Object>, Object> {

    private final Logger LOGGER = LoggerFactory.getLogger(LambdaSender.class);
    private String QUEUE_URL = System.getenv("QUEUE_URL");

    private final QueueMessagingTemplate queueMessagingTemplate;

    public LambdaSender(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    @Override
    public String handleRequest(Map<String, Object> message, Context context) {
        try{

            queueMessagingTemplate.send(QUEUE_URL, MessageBuilder.withPayload(message).build());
            LOGGER.info("Message sent to queue: " + message);

        }catch (Exception e){

            LOGGER.error("Error trying to send message: " + e.getMessage());
            new RuntimeException(e);
        }

        return null;
    }

}
