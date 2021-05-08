package com.nicico.cost.kafka.service.impl;

import com.nicico.cost.kafka.service.KafkaConsumerService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class ConsumerService implements KafkaConsumerService {

    private final List<String> kafkaMessages = new CopyOnWriteArrayList<>();

    private final List<Object> kafkaCustomMessage = new CopyOnWriteArrayList<>();

    public ConsumerFactory<String, Object> consumerFactory(Map<String, Object> configs) {
        return new DefaultKafkaConsumerFactory<>(configs);
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}", autoStartup = "${kafka.listener.enabled}")
    public void listen(ConsumerRecord<String, String> record) {
        kafkaMessages.add(record.value());
    }
}
