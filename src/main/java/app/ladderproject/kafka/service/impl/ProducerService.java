package app.ladderproject.kafka.service.impl;


import app.ladderproject.kafka.service.Handler;
import app.ladderproject.kafka.service.KafkaProducer;
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
public class ProducerService implements KafkaProducer {

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

    @Override
    public void send(String topic, Object o, Handler handler) {
        KafkaTemplate<String, Object> kafkaCustomTemplate = kafkaCustomTemplate(config);
        ListenableFuture<SendResult<String, Object>> future = kafkaCustomTemplate.send(topic, o);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                handler.onSuccess();
            }
            @Override
            public void onFailure(Throwable ex) {
                handler.onFailure(ex);
            }
        });
    }
}
