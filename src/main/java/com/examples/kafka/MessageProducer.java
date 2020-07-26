package com.examples.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Collections;
import java.util.Properties;

public class MessageProducer {
    public static void main(String[] args){
        sendMessage();
    }

    private static void receiveMessage(){
        Properties props = new Properties();
        String topic="test";
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 100);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String,String>(props);
        consumer.subscribe(Collections.singletonList(topic));

    }

    private static void sendMessage() {
        Properties props = new Properties();
        String topic="test";
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "client1");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String,String> producer = new KafkaProducer<String,String>(props);
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, "keyx", "valuex");
        try {
            RecordMetadata result= producer.send(record).get();
            producer.flush();
            producer.close();
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
