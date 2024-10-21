package ru.practicum.handler;

import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Component;
import ru.practicum.config.AppConfig;
import ru.practicum.config.KafkaEventProducer;
import ru.practicum.model.sensor.SensorEvent;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;

@Component
@RequiredArgsConstructor
public abstract class CommonSensorEventHandler<T extends SpecificRecordBase> {
    private final AppConfig config;
    private final KafkaEventProducer producer;

    protected abstract T mapToAvroObject(SensorEvent event);

    public void handle(SensorEvent event) {
        T avroObject = mapToAvroObject(event);
        SensorEventAvro sensorEventAvro = SensorEventAvro.newBuilder()
                .setId(event.getId())
                .setHubId(event.getHubId())
                .setTimestamp(event.getTimestamp())
                .setPayload(avroObject)
                .build();
        String topic = config.getTopicTelemetrySensors();
        producer.send(topic, sensorEventAvro);
    }
}
