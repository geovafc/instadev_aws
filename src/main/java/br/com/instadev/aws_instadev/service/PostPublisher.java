package br.com.instadev.aws_instadev.service;

import br.com.instadev.aws_instadev.enums.EventType;
import br.com.instadev.aws_instadev.model.Envelope;
import br.com.instadev.aws_instadev.model.Post;
import br.com.instadev.aws_instadev.model.PostEvent;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//Notação que diz para o spring injetar essa classe nos contrutores onde ela for chamada
@Service
public class PostPublisher {
    private static final Logger LOG = LoggerFactory.getLogger(PostPublisher.class);
// Usa o snsClient de acordo com o ambiente. Se for local, usa o da classe SnsCreate se for em dev
// usa o SnsConfig
    private AmazonSNS snsClient;
    private Topic postEventsTopic;
    private ObjectMapper objectMapper;

    //    @Qualifier uso ele para identificar com qual bean eu quero trabalhar
    public PostPublisher(AmazonSNS snsClient, @Qualifier("postEventsTopic") Topic postEventsTopic, ObjectMapper objectMapper ) {
        this.snsClient = snsClient;
        this.postEventsTopic = postEventsTopic;
        this.objectMapper = objectMapper;
    }

    public void publishPostEvent(Post post, EventType eventType, String username) throws JsonProcessingException {
        PostEvent postEvent = buildPostEvent(post);

        Envelope envelope = buildEnvelope(eventType, postEvent);

        snsClient.publish(
                postEventsTopic.getTopicArn(),
                objectMapper.writeValueAsString(envelope)
        );
    }

    private PostEvent buildPostEvent(Post post) {
        PostEvent postEvent = new PostEvent();
        postEvent.setPostId(post.getId());
        postEvent.setUserName(postEvent.getUserName());
        return postEvent;
    }


    private Envelope buildEnvelope(EventType eventType, PostEvent postEvent) {
        Envelope envelope = new Envelope();
        envelope.setEventType(eventType);

        try {
            envelope.setData(objectMapper.writeValueAsString(postEvent));
        } catch (JsonProcessingException e) {
            LOG.error("Failed to create post event message: ");
        }
        return  envelope;
    }

}
