package ru.practicum.handler.sensor;

import org.springframework.stereotype.Component;
import ru.practicum.annotation.HandlerSensorEvent;
import ru.practicum.config.AppConfig;
import ru.practicum.config.KafkaEventProducer;
import ru.practicum.handler.CommonSensorEventHandler;
import ru.practicum.model.sensor.LightSensorEvent;
import ru.practicum.model.sensor.SensorEvent;
import ru.practicum.model.sensor.enums.SensorEventType;
import ru.yandex.practicum.kafka.telemetry.event.LightSensorAvro;

@HandlerSensorEvent(SensorEventType.LIGHT_SENSOR_EVENT)
@Component
public class LightSensorEventHandler extends CommonSensorEventHandler<LightSensorAvro> {
    public LightSensorEventHandler(AppConfig config, KafkaEventProducer producer) {
        super(config, producer);
    }

    @Override
    protected LightSensorAvro mapToAvroObject(SensorEvent event) {
        LightSensorEvent sensorEvent = (LightSensorEvent) event;
        return LightSensorAvro.newBuilder()
                .setLinkQuality(sensorEvent.getLinkQuality())
                .setLuminosity(sensorEvent.getLuminosity())
                .build();
    }
}
