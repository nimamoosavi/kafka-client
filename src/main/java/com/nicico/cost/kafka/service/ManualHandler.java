package com.nicico.cost.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @author nima
 * @version 1.0.1
 * @apiNote this interface must be implement if you want to handle manual and
 * Example of implementation is in {@link com.nicico.cost.kafka.service.manual.ExampleHandler} and if not implement and you set kafka consumer manual
 * you return nullPointerException
 */
public interface ManualHandler {
    void handle(ConsumerRecord<String, Object> o);
}
