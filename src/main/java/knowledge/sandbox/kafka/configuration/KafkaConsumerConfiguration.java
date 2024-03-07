package knowledge.sandbox.kafka.configuration;

import knowledge.sandbox.kafka.Dto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
public class KafkaConsumerConfiguration {

    @Value("${sandbox.kafka.bootstrap-address}")
    private String bootstrapAddress;

    public ConsumerFactory<Long, Dto> consumerFactory(String groupId) {
        return new DefaultKafkaConsumerFactory<>(Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress,
                ConsumerConfig.GROUP_ID_CONFIG, groupId,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "20971520",
                ConsumerConfig.FETCH_MAX_BYTES_CONFIG, "20971520"
        ), new LongDeserializer(), new JsonDeserializer<>(Dto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, Dto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, Dto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory("groupId"));
        return factory;
    }

}
