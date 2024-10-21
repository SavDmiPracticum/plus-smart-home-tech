package ru.practicum.handler.hub;

import org.springframework.stereotype.Component;
import ru.practicum.annotation.HandlerHubEvent;
import ru.practicum.config.AppConfig;
import ru.practicum.config.KafkaEventProducer;
import ru.practicum.handler.CommonHubEventHandler;
import ru.practicum.model.hub.DeviceAddedEvent;
import ru.practicum.model.hub.HubEvent;
import ru.practicum.model.hub.enums.HubEventType;
import ru.yandex.practicum.kafka.telemetry.event.DeviceAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceTypeAvro;

@HandlerHubEvent(HubEventType.DEVICE_ADDED)
@Component
public class HubDeviceAddedEventHandler extends CommonHubEventHandler<DeviceAddedEventAvro> {

    public HubDeviceAddedEventHandler(AppConfig config, KafkaEventProducer producer) {
        super(config, producer);
    }

    @Override
    protected DeviceAddedEventAvro mapToAvroObject(HubEvent event) {
        DeviceAddedEvent deviceAddedEvent = (DeviceAddedEvent) event;
        return DeviceAddedEventAvro.newBuilder()
                .setId(deviceAddedEvent.getId())
                .setType(DeviceTypeAvro.valueOf(deviceAddedEvent.getType().name()))
                .build();
    }
}
