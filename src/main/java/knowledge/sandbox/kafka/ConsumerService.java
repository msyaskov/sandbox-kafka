package knowledge.sandbox.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@KafkaListener(topics = "some-topic")
public class ConsumerService {

    @KafkaHandler
    public void consume(Dto dto) {
        log.info("Received dto: {}", dto);
    }

}
