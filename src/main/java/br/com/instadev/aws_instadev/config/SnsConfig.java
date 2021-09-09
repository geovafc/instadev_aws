package br.com.instadev.aws_instadev.config;


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
//Quando você for executada em um perfil que é diferente de local, então eu executo essa config
@Profile("!local")
public class SnsConfig {

    @Value("{aws.region}")
    private String awsRegion;

    @Value("{aws.sns.topic.post.events.arn}")
    private String postEventsTopic;


//    Cliente responsável pela publicação da mensagem.
    @Bean
    public AmazonSNS snsClient(){
            return AmazonSNSClientBuilder.standard()
                    .withRegion(awsRegion)
//                    Credencial pra poder acessar o tópico. Vai procurar no contexto onde nosso
//                    serviço está executando credenciais que de permissão pra ele poder acessar
//                    um tópico
                    .withCredentials(new DefaultAWSCredentialsProviderChain())
                    .build();
    }

//    Config do tópico que eu quero publicar
    @Bean(name = "postEventsTopic")
    public Topic snsPostEventsTopic(){
        return new Topic().withTopicArn(postEventsTopic);
    }


}
