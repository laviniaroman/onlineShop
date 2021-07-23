package com.practice.onlineShop.controllers;

import com.practice.onlineShop.exceptions.*;
import com.practice.onlineShop.services.OrderService;
import com.practice.onlineShop.vos.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void addOrder(@RequestBody OrderVO orderVO) throws InvalidCustomerIdException, InvalidProductsException, InvalidProductIdException, NotEnoughStockException {
        orderService.addOrder(orderVO);
    }

    @PatchMapping("/{orderId}/{customerId}")
    public void deliver(@PathVariable Integer orderId, @PathVariable Long customerId) throws InvalidOrderIdException {
        orderService.deliver(orderId, customerId);

    }
}

