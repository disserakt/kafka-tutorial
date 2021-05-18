package ru.netology;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class Producer {

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        Properties config = new Properties();
        config.put("client.id", InetAddress.getLocalHost().getHostName() + "-1");
        config.put("bootstrap.servers", "localhost:9092");
        config.put("acks", "all");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        KafkaProducer<String, String> producer = new KafkaProducer<>(config);

        while (true) {
            final ProducerRecord<String, String> record = new ProducerRecord<>("test.topic.plain", "key", "Hello, world");
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
