package ru.yandex.practicum.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.order.dto.OrderDto;

import java.util.UUID;

@FeignClient(name = "order", path = "/api/v1/order")
public interface OrderClient {
    @PostMapping(value = "/payment")
    OrderDto payOrder(@RequestBody UUID orderId);

    @PostMapping("/payment/failed")
    OrderDto payOrderFailed(@RequestBody UUID orderId);

    @PostMapping("/completed")
    OrderDto completedOrder(@RequestBody UUID orderId);

    @PostMapping("/assembly")
    OrderDto assemblyOrder(@RequestBody UUID orderId);

    @PostMapping("/assembly/failed")
    OrderDto assemblyOrderFailed(@RequestBody UUID orderId);

    @PostMapping("/delivery/failed")
    OrderDto deliveryOrderFailed(@RequestBody UUID orderId);
}
