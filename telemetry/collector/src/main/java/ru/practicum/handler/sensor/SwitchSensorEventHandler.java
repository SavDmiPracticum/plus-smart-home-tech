package ru.practicum.handler.sensor;

import org.springframework.stereotype.Component;
import ru.practicum.annotation.HandlerSensorEvent;
import ru.practicum.config.AppConfig;
import ru.practicum.config.KafkaEventProducer;
import ru.practicum.handler.CommonSensorEventHandler;
import ru.practicum.model.sensor.SensorEvent;
import ru.practicum.model.sensor.SwitchSensorEvent;
import ru.practicum.model.sensor.enums.SensorEventType;
import ru.yandex.practicum.kafka.telemetry.event.SwitchSensorAvro;

@HandlerSensorEvent(SensorEventType.SWITCH_SENSOR_EVENT)
@Component
public class SwitchSensorEventHandler extends CommonSensorEventHandler<SwitchSensorAvro> {
    public SwitchSensorEventHandler(AppConfig config, KafkaEventProducer producer) {
        super(config, producer);
    }

    @Override
    protected SwitchSensorAvro mapToAvroObject(SensorEvent event) {
        SwitchSensorEvent sensorEvent = (SwitchSensorEvent) event;
        return SwitchSensorAvro.newBuilder()
                .setState(sensorEvent.getState())
                .build();
    }
}
