package app.ladderproject.kafka.service.manual;


import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * in class is example of handler implementation
 */
public class ExampleHandler /*implements ManualHandler*/ {

    /**
     * this method must be implement and kafka return data after consume
     * @param o the object of kafka consumer send
     */
    public void handle(ConsumerRecord<String, Object> o) {
        handleMethode(o);
    }

    public void handleMethode(ConsumerRecord<String, Object> o) {
        // you can get ConsumerRecord of Object in this method for example
    }
}
