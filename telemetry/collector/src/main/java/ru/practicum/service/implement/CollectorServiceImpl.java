package ru.practicum.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Service;
import ru.practicum.annotation.HandlerContext;
import ru.practicum.exception.NotFoundException;
import ru.practicum.handler.CommonHubEventHandler;
import ru.practicum.handler.CommonSensorEventHandler;
import ru.practicum.model.hub.HubEvent;
import ru.practicum.model.sensor.SensorEvent;
import ru.practicum.service.CollectorService;

@Service
@RequiredArgsConstructor
public class CollectorServiceImpl implements CollectorService {
    private final HandlerContext handlerContext;

    @Override
    public void collectSensorEvent(SensorEvent sensorEvent) {
        CommonSensorEventHandler<? extends SpecificRecordBase> commonSensorEventHandler = handlerContext
                .getSensorEventHandlers().get(sensorEvent.getType());
        if (commonSensorEventHandler != null) {
            commonSensorEventHandler.handle(sensorEvent);
        } else {
            throw new NotFoundException("Handler for sensor event type " + sensorEvent.getType() + " not found");
        }
    }

    @Override
    public void collectHubEvent(HubEvent hubEvent) {
        CommonHubEventHandler<? extends SpecificRecordBase> commonHubEventHandler = handlerContext
                .getHubEventHandlers().get(hubEvent.getType());
        if (commonHubEventHandler != null) {
            commonHubEventHandler.handle(hubEvent);
        } else {
            throw new NotFoundException("Handler for hub event type " + hubEvent.getType() + " not found");
        }
    }
}