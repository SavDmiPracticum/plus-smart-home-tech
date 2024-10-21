package ru.practicum.handler.hub;

import org.springframework.stereotype.Component;
import ru.practicum.annotation.HandlerHubEvent;
import ru.practicum.config.AppConfig;
import ru.practicum.config.KafkaEventProducer;
import ru.practicum.handler.CommonHubEventHandler;
import ru.practicum.model.hub.HubEvent;
import ru.practicum.model.hub.ScenarioRemovedEvent;
import ru.practicum.model.hub.enums.HubEventType;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioRemovedEventAvro;

@Component
@HandlerHubEvent(HubEventType.SCENARIO_REMOVED)
public class HubScenarioRemovedEventHandler extends CommonHubEventHandler<ScenarioRemovedEventAvro> {
    public HubScenarioRemovedEventHandler(AppConfig config, KafkaEventProducer producer) {
        super(config, producer);
    }

    @Override
    protected ScenarioRemovedEventAvro mapToAvroObject(HubEvent event) {
        ScenarioRemovedEvent removedEvent = (ScenarioRemovedEvent) event;
        return ScenarioRemovedEventAvro.newBuilder()
                .setName(removedEvent.getName())
                .build();
    }
}
