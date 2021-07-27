package com.nicico.cost.kafka.service;

import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.packages.kafka.KafkaProducer;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

/**
 * @author nima mousavi
 * @author Hossein Mahdevar
 * @version 1.0.1
 * @implNote use Kafka producer to impl such as spring kafka
 * @since 1.0.1
 */
public interface KafkaProducerService extends KafkaProducer {

    /**
     * @param configs that is custom config if you need
     * @return ProducerFactory for produceConfig
     * if you want set custom you must implement some key such as ProducerConfig.BOOTSTRAP_SERVERS_CONFIG ,
     * ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG and etc but some key is required to connect to kafka
     */
    ProducerFactory<String, Object> producerFactory(Map<String, Object> configs);

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
    BaseDTO<Boolean> sendSynchronous(String topic, Object o);

    /**
     * @param topic  of kafka that you must import it
     * @param o      object that serialize and push it to kafka server
     * @param config that is the kafka config for connect
     * @implNote method call producer async and not return response
     */
    void send(String topic, Object o, ProducerFactory<String, Object> config);

    /**
     * @param topic  of kafka that you must import it
     * @param o      object that serialize and push it to kafka server
     * @param config that is the kafka config for connect
     * @return boolean response of call kafka server
     * @apiNote : you must know method not call server Async and it can delay for get response but you can manage result of kafka
     */
    BaseDTO<Boolean> sendSynchronous(String topic, String o, ProducerFactory<String, Object> config);

}
