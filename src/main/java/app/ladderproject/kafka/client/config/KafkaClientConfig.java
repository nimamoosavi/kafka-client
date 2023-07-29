package app.ladderproject.kafka.client.config;

import lombok.Builder;

@Builder
public class KafkaClientConfig {

    private final String address;

}
