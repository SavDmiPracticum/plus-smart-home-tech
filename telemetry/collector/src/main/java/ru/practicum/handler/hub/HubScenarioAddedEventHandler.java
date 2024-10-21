package ru.practicum.handler.hub;

import org.springframework.stereotype.Component;
import ru.practicum.annotation.HandlerHubEvent;
import ru.practicum.config.AppConfig;
import ru.practicum.config.KafkaEventProducer;
import ru.practicum.handler.CommonHubEventHandler;
import ru.practicum.model.hub.HubEvent;
import ru.practicum.model.hub.ScenarioAddedEvent;
import ru.practicum.model.hub.enums.HubEventType;
import ru.yandex.practicum.kafka.telemetry.event.*;

import java.util.List;

@Component
@HandlerHubEvent(HubEventType.SCENARIO_ADDED)
public class HubScenarioAddedEventHandler extends CommonHubEventHandler<ScenarioAddedEventAvro> {
    public HubScenarioAddedEventHandler(AppConfig config, KafkaEventProducer producer) {
        super(config, producer);
    }

    @Override
    protected ScenarioAddedEventAvro mapToAvroObject(HubEvent event) {
        ScenarioAddedEvent scenarioAddedEvent = (ScenarioAddedEvent) event;

        return ScenarioAddedEventAvro.newBuilder()
                .setName(scenarioAddedEvent.getName())
                .setActions(getDeviceActionAvroList(scenarioAddedEvent))
                .setConditions(getScenarioConditionAvroList(scenarioAddedEvent))
                .build();
    }

    private List<ScenarioConditionAvro> getScenarioConditionAvroList(ScenarioAddedEvent scenarioAddedEvent) {
        return scenarioAddedEvent.getConditions().stream()
                .map(c -> ScenarioConditionAvro.newBuilder()
                        .setSensorId(c.getSensorId())
                        .setType(ConditionTypeAvro.valueOf(c.getType().name()))
                        .setOperation(ConditionOperationAvro.valueOf(c.getOperation().name()))
                        .setValue(c.getValue())
                        .build())
                .toList();
    }

    private List<DeviceActionAvro> getDeviceActionAvroList(ScenarioAddedEvent scenarioAddedEvent) {
        return scenarioAddedEvent.getActions().stream()
                .map(a -> DeviceActionAvro.newBuilder()
                        .setSensorId(a.getSensorId())
                        .setType(ActionTypeAvro.valueOf(a.getType().name()))
                        .setValue(a.getValue())
                        .build())
                .toList();
    }
}
