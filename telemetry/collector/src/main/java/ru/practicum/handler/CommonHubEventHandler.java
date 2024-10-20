package ru.practicum.handler;

import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Component;
import ru.practicum.config.AppConfig;
import ru.practicum.config.KafkaEventProducer;
import ru.practicum.model.hub.HubEvent;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;

@Component
@RequiredArgsConstructor
public abstract class CommonHubEventHandler<T extends SpecificRecordBase> {
    private final AppConfig config;
    private final KafkaEventProducer producer;

    protected abstract T mapToAvroObject(HubEvent event);

    public void handle(HubEvent event) {
        T avroObject = mapToAvroObject(event);
        HubEventAvro hubEventAvro = HubEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setTimestamp(event.getTimestamp())
                .setPayload(avroObject)
                .build();
        String topic = config.getTopicTelemetryHubs();
        producer.send(topic, hubEventAvro);
    }
}
