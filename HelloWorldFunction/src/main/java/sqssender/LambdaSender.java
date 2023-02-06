package sqssender;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.slf4j.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;

/**ß
 * Handler for requests to Lambda function.
 */
public class LambdaSender implements RequestHandler<Map<String, String>, String> {

    private final Logger LOGGER = LoggerFactory.getLogger(LambdaSender.class);
    private String QUEUE_URL = System.getenv("QUEUE_URL");

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Override
    public String handleRequest(Map<String, String> message, Context context) {
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
