package sqssender;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2CustomAuthorizerEvent;
import org.slf4j.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import sqssender.sqs.SqsSender;

import java.util.Map;

/**ß
 * Handler for requests to Lambda function.
 */
public class LambdaSender implements RequestHandler<Map<String, String>, String> {

    private final Logger LOGGER = LoggerFactory.getLogger(LambdaSender.class);
    private final SqsSender sender = new SqsSender();

    /**
     * This Lambda function simply receives a message and send to a SQS queue
     * @param message The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return
     */
    @Override
    public String handleRequest(Map<String, String> message, Context context) {
        try{
            LOGGER.info("Starting to send message");
            sender.sendToQueue(message.get("message"));
            LOGGER.info("Finished sending message");

            return "Message was sended successfully";

        }catch (Exception e){

            LOGGER.error("Error after to send lambda: " + e.getMessage());
            new RuntimeException(e);
            return null;
        }
    }

}
