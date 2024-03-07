# Kafka Sandbox
Песочница для ознакомления с Kafka при использовании Spring Boot.

## Установка
Запускаем [docker-compose.yml](./docker-compose.yml) с помощью IDEA или `docker compose up -d`

Если кафка не поднялась, то запустите её вручную после инициализации контейнера `zookeeper`.

После чего запустите приложение Spring Boot.

## Примечание
Топик в кафке создавать не надо, это делается во время конфигурации приложения (см [KafkaTopicConfiguration](./src/main/java/knowledge/sandbox/kafka/configuration/KafkaTopicConfiguration.java)).

[KafkaProducerConfiguration](./src/main/java/knowledge/sandbox/kafka/configuration/KafkaTopicConfiguration.java) конфигурирует `KafkaTemplate`, через который отправляем сообщения в кафку. 
В песочнице сообщение - это экземпляр класса [Dto](./src/main/java/knowledge/sandbox/kafka/Dto.java).
[ProducerService](./src/main/java/knowledge/sandbox/kafka/ProducerService.java) - сервис для отправки сообщений в кафку.
В [SandboxKafkaApplication](./src/main/java/knowledge/sandbox/kafka/SandboxKafkaApplication.java), как раз используется этот сервис для отправки сообщения.

[KafkaConsumerConfiguration](./src/main/java/knowledge/sandbox/kafka/configuration/KafkaConsumerConfiguration.java) конфигурирует `ConcurrentKafkaListenerContainerFactory` для потребителей сообщений.
Потребление сообщение реализуется сервисом [ConsumerService](./src/main/java/knowledge/sandbox/kafka/ConsumerService.java) с помощью KafkaHandler.
Обратите внимание на аннотации в этом классе.

Приложение отправляет одно сообщение в кафку, и принимает это же сообщение из кафки.
Смотрите в логах сообщения: `Dto produced`, `Received dto: ...`.