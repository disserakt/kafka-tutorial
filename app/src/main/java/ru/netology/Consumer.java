package ru.netology;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import ru.netology.event.TestEvent;
import ru.netology.serde.TestEventDeserializer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args) throws UnknownHostException {
        Properties config = new Properties();
        config.put("client.id", InetAddress.getLocalHost().getHostName() + "-2");
        config.put("group.id", "foo");
        config.put("bootstrap.servers", "localhost:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, TestEventDeserializer.class);

        try (KafkaConsumer<String, TestEvent> consumer = new KafkaConsumer<>(config)) {
            consumer.subscribe(Collections.singletonList("test.topic.json"));
            while (true) {
                ConsumerRecords<String, TestEvent> records = consumer.poll(Duration.ofMillis(500));
                records.forEach(record -> System.out.println("Record: " + record));
                consumer.commitAsync();
            }
        }
    }

}
