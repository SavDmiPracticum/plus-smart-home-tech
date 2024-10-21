package ru.practicum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.model.hub.HubEvent;
import ru.practicum.model.sensor.SensorEvent;
import ru.practicum.service.CollectorService;

@RestController
@RequestMapping("/events")
@Slf4j
@RequiredArgsConstructor
public class CollectorController {
    private final CollectorService collectorService;

    @PostMapping("/sensors")
    public void collectSensors(@Valid @RequestBody SensorEvent sensorEvent) {
        log.info("Sensor event: {}", sensorEvent);
        collectorService.collectSensorEvent(sensorEvent);
    }

    @PostMapping("/hubs")
    public void collectHubs(@Valid @RequestBody HubEvent hubEvent) {
        log.info("Hub event: {}", hubEvent);
        collectorService.collectHubEvent(hubEvent);
    }
}
