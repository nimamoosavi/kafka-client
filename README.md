### this project use for connect to kafka with tcp protocol and produce and consume data and implement by spring framework

### Requirements
The library works with Java 8+, ladder Core 1.0.1+

## [Core](https://github.com/nimamoosavi/core/wiki)


## Structure

![kafka Diagram](https://github.com/nimamoosavi/kafka/wiki/images/kafka.png)


- [kafkaConsumer](KafkaConsumer)
- [kafkaProducer](KafkaProducer)

default class

## Config

you can change this config with your application properties

> kafka.bootstrap:localhost=9092
>
> kafka.producer.ack=1
>
> kafka.consumer.groupId=kafka
>
> kafka.consumer.autoOffsetReset=earliest
>
> kafka.consumer.deserializer.class=StringSerializer.class
>
> kafka.consumer.key.deserializer.class:StringDeSerializer
>
> kafka.producer.serialize.class=StringSerializer.class
>
> kafka.producer.key.serialize.class=StringSerializer.class
