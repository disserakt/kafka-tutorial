package ru.netology.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import ru.netology.event.TestEvent;

import java.io.IOException;

public class TestEventDeserializer implements Deserializer<TestEvent> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public TestEvent deserialize(String topic, byte[] data) {
        try {
            if (data == null)
                return null;
            else
                return mapper.readValue(data, TestEvent.class);
        } catch (IOException e) {
            throw new SerializationException("Error when deserializing byte[] to TestEvent. String input was: " + new String(data));
        }
    }

}
