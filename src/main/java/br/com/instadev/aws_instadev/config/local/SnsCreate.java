package br.com.instadev.aws_instadev.config.local;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
//Classe usada somente para criar o tópico para fazer testes local
public class SnsCreate {
    private static final Logger LOG = LoggerFactory.getLogger(SnsCreate.class);

    private final String postEventsTopic;

    private final AmazonSNS snsClient;

    public SnsCreate() {
//        Criando o cliente do SNS que vai acessar o tópico que está na minha máquina
        this.snsClient = AmazonSNSClient.builder()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration("http://localhost:4566",
                        Regions.US_EAST_1.getName()))
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

        postEventsTopic = createTopic();
    }

    private String createTopic() {
        final String postEventsTopic;
        CreateTopicRequest createTopicRequest = new CreateTopicRequest("post-events");
        postEventsTopic = this.snsClient.createTopic(createTopicRequest).getTopicArn();

        LOG.info("SNS topic ARN: {}", postEventsTopic);
        return postEventsTopic;
    }



    @Bean
    public AmazonSNS snsClient(){
        return this.snsClient;
    }

    @Bean(name = "postEventsTopic")
    public Topic snsPostEventsTopic(){
        return new Topic().withTopicArn(postEventsTopic);
    }


}
