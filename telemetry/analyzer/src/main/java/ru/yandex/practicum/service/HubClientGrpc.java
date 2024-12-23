package ru.yandex.practicum.service;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.grpc.telemetry.event.ActionTypeProto;
import ru.yandex.practicum.grpc.telemetry.event.DeviceActionProto;
import ru.yandex.practicum.grpc.telemetry.event.DeviceActionRequestProto;
import ru.yandex.practicum.grpc.telemetry.hubrouter.HubRouterControllerGrpc;
import ru.yandex.practicum.model.Action;

@Service
@Slf4j
public class HubClientGrpc {

    private final HubRouterControllerGrpc.HubRouterControllerBlockingStub stub;

    public HubClientGrpc(@GrpcClient("hub-router") HubRouterControllerGrpc.HubRouterControllerBlockingStub stub) {
        this.stub = stub;
    }

    public void sendAction(Action action, String hubId) {
        DeviceActionRequestProto actionRequestProto = DeviceActionRequestProto.newBuilder()
                .setHubId(hubId)
                .setAction(DeviceActionProto.newBuilder()
                        .setSensorId(action.getSensor().getId())
                        .setType(ActionTypeProto.valueOf(action.getType().name()))
                        .setValue(action.getValue())
                        .build())
                .build();
        stub.handleDeviceAction(actionRequestProto);
        log.info("Message sent to Hub Router: {} sensor: {} action: {}",
                hubId, action.getSensor().getId(), action.getType().name());
    }


}
