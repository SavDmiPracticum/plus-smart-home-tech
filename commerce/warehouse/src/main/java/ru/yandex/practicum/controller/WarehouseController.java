package ru.yandex.practicum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.service.WarehouseService;
import ru.yandex.practicum.shoppingCart.dto.BookedProductsDto;
import ru.yandex.practicum.shoppingCart.dto.ShoppingCartDto;
import ru.yandex.practicum.warehouse.dto.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/warehouse")
@Slf4j
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;

    @PutMapping
    public void newProductToWarehouse(@RequestBody @Valid NewProductInWarehouseRequestDto requestDto) {
        log.info("Adding new product to warehouse {}", requestDto);
        warehouseService.newProductToWarehouse(requestDto);
    }

    @PostMapping("/return")
    public void returnProductToWarehouse(@RequestBody Map<UUID, Long> products) {
        log.info("Returning product to warehouse {}", products);
        warehouseService.returnProductToWarehouse(products);
    }

    @PostMapping("/booking")
    public BookedProductsDto bookProduct(@RequestBody @Valid ShoppingCartDto shoppingCartDto) {
        log.info("Booking products {}", shoppingCartDto);
        return warehouseService.bookProduct(shoppingCartDto);
    }

    @PostMapping("/assembly")
    public BookedProductsDto assemblyProductForOrder(@RequestBody @Valid AssemblyProductForOrderFromShoppingCartDto
                                                             assemblyProductDto) {
        log.info("Assembly product {}", assemblyProductDto);
        return warehouseService.assemblyProductForOrder(assemblyProductDto);
    }

    @PostMapping("/add")
    public void addProductToWarehouse(@RequestBody @Valid AddProductToWarehouseRequest requestDto) {
        log.info("Adding product to warehouse {}", requestDto);
        warehouseService.addProductToWarehouse(requestDto);
    }

    @GetMapping("/address")
    public AddressDto getAddress() {
        log.info("Getting address");
        return warehouseService.getAddress();
    }

    @PostMapping("/shipped")
    public void shippedToDelivery(ShippedToDeliveryRequest request) {
        log.info("Shipping to {}", request);
        warehouseService.shippedToDelivery(request);
    }

}
