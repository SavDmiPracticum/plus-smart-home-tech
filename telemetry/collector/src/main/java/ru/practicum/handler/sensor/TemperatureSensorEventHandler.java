package ru.practicum.handler.sensor;

import org.springframework.stereotype.Component;
import ru.practicum.annotation.HandlerSensorEvent;
import ru.practicum.config.AppConfig;
import ru.practicum.config.KafkaEventProducer;
import ru.practicum.handler.CommonSensorEventHandler;
import ru.practicum.model.sensor.SensorEvent;
import ru.practicum.model.sensor.TemperatureSensorEvent;
import ru.practicum.model.sensor.enums.SensorEventType;
import ru.yandex.practicum.kafka.telemetry.event.TemperatureSensorAvro;

@HandlerSensorEvent(SensorEventType.TEMPERATURE_SENSOR_EVENT)
@Component
public class TemperatureSensorEventHandler extends CommonSensorEventHandler<TemperatureSensorAvro> {
    public TemperatureSensorEventHandler(AppConfig config, KafkaEventProducer producer) {
        super(config, producer);
    }

    @Override
    protected TemperatureSensorAvro mapToAvroObject(SensorEvent event) {
        TemperatureSensorEvent sensorEvent = (TemperatureSensorEvent) event;
        return TemperatureSensorAvro.newBuilder()
                .setTemperatureC(sensorEvent.getTemperatureC())
                .setTemperatureF(sensorEvent.getTemperatureF())
                .build();
    }
}
