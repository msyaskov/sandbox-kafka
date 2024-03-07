package knowledge.sandbox.kafka;

import knowledge.sandbox.kafka.Dto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class ProducerService {

    private final KafkaTemplate<Long, Dto> kafkaTemplate;

    @Value("${sandbox.kafka.topic}")
    private String topic;

    public CompletableFuture<SendResult<Long, Dto>> produce(Dto dto) {
        return kafkaTemplate.send(topic, dto);
    }

}
