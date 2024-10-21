package ru.practicum.handler.hub;

import org.springframework.stereotype.Component;
import ru.practicum.annotation.HandlerHubEvent;
import ru.practicum.config.AppConfig;
import ru.practicum.config.KafkaEventProducer;
import ru.practicum.handler.CommonHubEventHandler;
import ru.practicum.model.hub.DeviceRemovedEvent;
import ru.practicum.model.hub.HubEvent;
import ru.practicum.model.hub.enums.HubEventType;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedEventAvro;

@Component
@HandlerHubEvent(HubEventType.DEVICE_REMOVED)
public class HubDeviceRemovedEventHandler extends CommonHubEventHandler<DeviceRemovedEventAvro> {
    public HubDeviceRemovedEventHandler(AppConfig config, KafkaEventProducer producer) {
        super(config, producer);
    }

    @Override
    protected DeviceRemovedEventAvro mapToAvroObject(HubEvent event) {
        DeviceRemovedEvent removedEvent = (DeviceRemovedEvent) event;
        return DeviceRemovedEventAvro.newBuilder()
                .setId(removedEvent.getId())
                .build();
    }
}
