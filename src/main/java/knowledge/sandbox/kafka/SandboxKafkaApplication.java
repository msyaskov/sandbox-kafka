package knowledge.sandbox.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class SandboxKafkaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SandboxKafkaApplication.class, args);
    }

    private final ProducerService producerService;

    @Override
    public void run(String... args) throws Exception {
        producerService.produce(new Dto("dto-value")).whenComplete((sr, t) -> {
            if (t != null) {
                log.error("Can't produce dto", t);
            } else {
                log.info("Dto produced");
            }
        });
    }
}
