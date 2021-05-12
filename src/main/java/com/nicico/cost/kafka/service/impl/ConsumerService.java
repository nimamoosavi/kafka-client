package com.nicico.cost.kafka.service.impl;

import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.kafka.service.KafkaConsumerService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.nicico.cost.framework.service.GeneralResponse.successCustomResponse;

@Service
@RequiredArgsConstructor
public class ConsumerService implements KafkaConsumerService {

    private final List<Object> kafkaCustomMessage = new CopyOnWriteArrayList<>();


    @KafkaListener(topics = "${kafka.topic}",
            groupId = "${kafka.consumer.group-id}",
            autoStartup = "${kafka.listener.enabled}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(ConsumerRecord<String, String> record) {
        kafkaCustomMessage.add(record.value());
    }


    public List<Object> kafkaMessages() {
        return kafkaCustomMessage;
    }

    public synchronized BaseDTO<Boolean> removeAll() {
        kafkaCustomMessage.clear();
        return successCustomResponse(true);
    }

    public synchronized Object getLastMessage() {
        int size = kafkaCustomMessage.size();
        if (size - 1 >= 0)
            return kafkaCustomMessage.get(size - 1);
        return null;
    }

    public synchronized <R> R getLastMessage(Class<R> tClass) {
        int size = kafkaCustomMessage.size();
        if (size - 1 >= 0)
            return tClass.cast(kafkaCustomMessage.get(size - 1));
        return null;
    }

    public synchronized Object getFirstMessage() {
        return kafkaCustomMessage.get(0);
    }

    public synchronized <R> R getFirstMessage(Class<R> tClass) {
        return tClass.cast(kafkaCustomMessage.get(0));
    }

    public synchronized Object getAndRemoveFirstMessage() {
        Object o = kafkaCustomMessage.get(0);
        if (o != null)
            kafkaCustomMessage.remove(0);
        return o;
    }

    public synchronized <R> R getAndRemoveFirstMessage(Class<R> tClass) {
        Object o = kafkaCustomMessage.get(0);
        if (o != null)
            kafkaCustomMessage.remove(0);
        return tClass.cast(o);
    }

    public synchronized Object getAndRemoveLastMessage() {
        int size = kafkaCustomMessage.size();
        if (size - 1 >= 0) {
            Object o = kafkaCustomMessage.get(size - 1);
            kafkaCustomMessage.remove(size - 1);
            return o;
        }
        return null;
    }

    public synchronized <R> R getAndRemoveLastMessage(Class<R> tClass) {
        int size = kafkaCustomMessage.size();
        if (size - 1 >= 0) {
            Object o = kafkaCustomMessage.get(size - 1);
            kafkaCustomMessage.remove(size - 1);
            return tClass.cast(o);
        }
        return null;
    }

    public int messagesList() {
        return kafkaCustomMessage.size();
    }

    public synchronized Object getMessage(int index) {
        try {
            return kafkaCustomMessage.get(index - 1);
        } catch (Exception e) {
            return null;
        }
    }

    public synchronized <R> R getMessage(int index, Class<R> tClass) {
        try {
            return tClass.cast(kafkaCustomMessage.get(index - 1));
        } catch (Exception e) {
            return null;
        }
    }

}
