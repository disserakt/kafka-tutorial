package ru.netology;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import ru.netology.event.TestEvent;
import ru.netology.serde.TestEventSerializer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.Properties;
import java.util.UUID;

public class Producer {

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        Properties config = new Properties();
        config.put("client.id", InetAddress.getLocalHost().getHostName() + "-1");
        config.put("bootstrap.servers", "localhost:9092");
        config.put("acks", "all");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TestEventSerializer.class);
        KafkaProducer<String, TestEvent> producer = new KafkaProducer<>(config);

        while (true) {
            var testEvent = new TestEvent("Hello, world!", Instant.now().toEpochMilli());
            final ProducerRecord<String, TestEvent> record = new ProducerRecord<>("test.topic.json", UUID.randomUUID().toString(), testEvent);
            System.out.println("Record: " + record);
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e != null) {
                        System.out.println("Send failed for record: " + record);
                        System.out.println(e.getMessage());
                        System.out.println(e.getStackTrace());
                    }
                }
            });
            Thread.sleep(5000);
        }
    }

}
