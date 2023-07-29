package app.ladderproject.kafka.service;

/**
 * @author nima mousavi
 * @author Hossein Mahdevar
 * @version 1.0.1
 * @implNote use Kafka producer to impl such as spring kafka
 * @since 1.0.1
 */
public interface KafkaProducer {

    /**
     * @param topic of kafka that you must import it
     * @param o     object that serialize and push it to kafka server
     * @implNote method call producer async and not return response
     */
    void send(String topic, Object o);

    /**
     * @param topic of kafka that you must import it
     * @param o     object that serialize and push it to kafka server
     * @return boolean response of call kafka server
     * @apiNote : you must know method not call server Async and it can delay for get response but you can manage result of kafka
     */
    void send(String topic, Object o,Handler handler);
}
