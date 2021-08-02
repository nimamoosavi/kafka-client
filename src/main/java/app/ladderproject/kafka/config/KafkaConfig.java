package app.ladderproject.kafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Value("${kafka.bootstrap:localhost:9092}")
    private String bootstrapAddress;

    @Value("${kafka.producer.ack:1}")
    private String ack;

    @Value("${kafka.consumer.groupId:kafka}")
    private String groupId;

    @Value("${kafka.consumer.autoOffsetReset:earliest}")
    private String autoOffsetReset;

    @Value("${kafka.consumer.deserializer.class:}")
    private String deserializeClazz;

    @Value("${kafka.consumer.key.deserializer.class:}")
    private String keyDeserializeClazz;

    @Value("${kafka.producer.serialize.class:}")
    private String serializeClazz;

    @Value("${kafka.producer.key.serialize.class:}")
    private String keySerializeClazz;


    @Bean
    public KafkaAdmin kafkaConfig() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public ProducerFactory<String, Object> producerDefaultFactory() throws ClassNotFoundException {
        Class<?> aClass;
        Class<?> keyAClass;
        Map<String, Object> producerConfig = new HashMap<>();
        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        if (Boolean.FALSE.equals(keySerializeClazz == null))
            keyAClass = Class.forName(keySerializeClazz);
        else
            keyAClass = StringSerializer.class;
        producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keyAClass);
        if (Boolean.FALSE.equals(serializeClazz == null))
            aClass = Class.forName(serializeClazz);
        else
            aClass = StringSerializer.class;
        producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, aClass);
        producerConfig.put(ProducerConfig.ACKS_CONFIG, ack);
        return new DefaultKafkaProducerFactory<>(producerConfig);
    }

    @Bean
    public ConsumerFactory<String, Object> consumerDefaultFactory() throws ClassNotFoundException {
        Class<?> aClass;
        Class<?> keyAClass;
        Map<String, Object> consumerConfig = new HashMap<>();
        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        if (Boolean.FALSE.equals(keyDeserializeClazz == null))
            keyAClass = Class.forName(keyDeserializeClazz);
        else
            keyAClass = StringDeserializer.class;
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyAClass);
        if (Boolean.FALSE.equals(deserializeClazz == null))
            aClass = Class.forName(deserializeClazz);
        else
            aClass = StringDeserializer.class;
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, aClass);
        consumerConfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        return new DefaultKafkaConsumerFactory<>(consumerConfig);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() throws ClassNotFoundException {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerDefaultFactory());
        return factory;
    }


}
