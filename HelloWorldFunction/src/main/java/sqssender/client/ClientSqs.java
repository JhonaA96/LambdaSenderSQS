package sqssender.client;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

public class ClientSqs {

    private String ACCES_KEY = System.getenv("ACCES_KEY");
    private String SECRET_KEY = System.getenv("SECRET_KEY");

    /**
     * This method is responsible to create a SQS client
     * @return
     */
    public AmazonSQSAsync amazonSQSAsyncClientBuilder() {
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCES_KEY, SECRET_KEY)))
                .build();
    }
}
