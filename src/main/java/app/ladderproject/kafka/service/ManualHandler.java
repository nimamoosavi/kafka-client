package app.ladderproject.kafka.service;

import app.ladderproject.kafka.service.manual.ExampleHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @author nima
 * @version 1.0.1
 * @apiNote this interface must be implemented if you want to handle manual and
 * Example of implementation is in {@link ExampleHandler} and if not implement, and you set kafka consumer manual
 * you return nullPointerException
 */
public interface ManualHandler {
    void handle(ConsumerRecord<String, Object> o);
}
