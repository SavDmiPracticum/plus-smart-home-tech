package ru.practicum.handler.sensor;

import org.springframework.stereotype.Component;
import ru.practicum.annotation.HandlerSensorEvent;
import ru.practicum.config.AppConfig;
import ru.practicum.config.KafkaEventProducer;
import ru.practicum.handler.CommonSensorEventHandler;
import ru.practicum.model.sensor.MotionSensorEvent;
import ru.practicum.model.sensor.SensorEvent;
import ru.practicum.model.sensor.enums.SensorEventType;
import ru.yandex.practicum.kafka.telemetry.event.MotionSensorAvro;

@HandlerSensorEvent(SensorEventType.MOTION_SENSOR_EVENT)
@Component
public class MotionSensorEventHandler extends CommonSensorEventHandler<MotionSensorAvro> {
    public MotionSensorEventHandler(AppConfig config, KafkaEventProducer producer) {
        super(config, producer);
    }

    @Override
    protected MotionSensorAvro mapToAvroObject(SensorEvent event) {
        MotionSensorEvent sensorEvent = (MotionSensorEvent) event;
        return MotionSensorAvro.newBuilder()
                .setMotion(sensorEvent.getMotion())
                .setLinkQuality(sensorEvent.getLinkQuality())
                .setVoltage(sensorEvent.getVoltage())
                .build();
    }
}
