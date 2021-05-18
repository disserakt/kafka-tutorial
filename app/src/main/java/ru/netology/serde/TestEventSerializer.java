package ru.netology.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import ru.netology.event.TestEvent;

public class TestEventSerializer implements Serializer<TestEvent> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, TestEvent data) {
        try {
            if (data == null)
                return null;
            else
                return mapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Error when serializing TestEvent to byte[]. Test event: " + data);
        }
    }
}
