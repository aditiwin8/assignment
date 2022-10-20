package com.project.assignment.controller;

import com.project.assignment.dto.CreateOrderRequest;
import com.project.assignment.dto.OrderResponse;
import com.project.assignment.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> createOrders(@RequestBody List<CreateOrderRequest> createOrderRequests) {
        this.orderService.createOrders(createOrderRequests);
        return ok().build();
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        var orders = orderService.getAllOrders();
        return ok(orders);
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        var orderResponse = orderService.getOrderById(orderId);

        if (orderResponse == null) {
            return notFound().build();
        }

        return ok(orderResponse);
    }
}
