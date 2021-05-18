package ru.netology.event;

public class TestEvent {

    public TestEvent() {
    }

    public TestEvent(String payload, long timestamp) {
        this.payload = payload;
        this.timestamp = timestamp;
    }

    public String payload;
    public long timestamp;

    @Override
    public String toString() {
        return "TestEvent{" +
                "payload='" + payload + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}
