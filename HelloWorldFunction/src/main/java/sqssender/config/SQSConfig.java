package sqssender.config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SQSConfig {

    private String ACCES_KEY = System.getenv("ACCES_KEY");
    private String SECRET_KEY = System.getenv("SECRET_KEY");

    @Bean
    public QueueMessagingTemplate SQSAuditoriaTemplate() {
        return new QueueMessagingTemplate(amazonSQSAsyncClientBuilder());
    }

    private AmazonSQSAsync amazonSQSAsyncClientBuilder() {
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCES_KEY, SECRET_KEY)))
                .build();
    }
}
