package com.webold.kafka.service.impl;

import com.webold.framework.domain.dto.BaseDTO;
import com.webold.framework.enums.Status;
import com.webold.kafka.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProducerService implements KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ProducerFactory<String, Object> producerFactory(Map<String, Object> configs) {
        return new DefaultKafkaProducerFactory<>(configs);
    }

    private KafkaTemplate<String, Object> kafkaCustomTemplate(ProducerFactory<String, Object> config) {
        return new KafkaTemplate<>(config);
    }

    public void send(String topic, Object o) {
        kafkaTemplate.send(topic, o);
    }

    public BaseDTO<Boolean> sendSynchronous(String topic, Object o) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, o);
        BaseDTO.BaseDTOBuilder<Boolean> builder = BaseDTO.<Boolean>builder();
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                builder.data(true).status(Status.SUCCESS);
            }

            @Override
            public void onFailure(Throwable ex) {
                builder.data(false).status(Status.ERROR);
            }
        });
        return builder.build();
    }

    public void send(String topic, Object o, ProducerFactory<String, Object> config) {
        KafkaTemplate<String, Object> kafkaCustomTemplate = kafkaCustomTemplate(config);
        kafkaCustomTemplate.send(topic, o);
    }

    public BaseDTO<Boolean> sendSynchronous(String topic, String o, ProducerFactory<String, Object> config) {
        KafkaTemplate<String, Object> kafkaCustomTemplate = kafkaCustomTemplate(config);
        ListenableFuture<SendResult<String, Object>> future = kafkaCustomTemplate.send(topic, o);
        BaseDTO.BaseDTOBuilder<Boolean> builder = BaseDTO.<Boolean>builder();
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                builder.data(true).status(Status.SUCCESS);
            }

            @Override
            public void onFailure(Throwable ex) {
                builder.data(false).status(Status.ERROR);
            }
        });
        return builder.build();
    }
}
