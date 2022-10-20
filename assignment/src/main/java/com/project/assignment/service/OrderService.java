package com.project.assignment.service;

import com.project.assignment.data.entity.Order;
import com.project.assignment.data.entity.OrderItem;
import com.project.assignment.data.repository.OrderRepository;
import com.project.assignment.dto.CreateOrderRequest;
import com.project.assignment.dto.OrderItemResponse;
import com.project.assignment.dto.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrders(List<CreateOrderRequest> createOrderRequestList) {
        var orderList = createOrderRequestList.stream().map(order -> Order.builder()
                        .orderDate(order.getOrderDate())
                        .orderStatus("New")
                        .collectionOfItems(order.getItems().stream().map(orderItem -> OrderItem.builder()
                                        .itemName(orderItem.getItemName())
                                        .itemQuantity(orderItem.getItemQuantity())
                                        .itemUnitPrice(orderItem.getItemUnitPrice())
                                        .build())
                                .toList())
                        .build())
                .toList();

        orderRepository.saveAll(orderList);
    }

    public List<OrderResponse> getAllOrders() {
        var orderList = orderRepository.findAll();

        return orderList.stream().map(order -> OrderResponse.builder()
                        .orderDate(order.getOrderDate())
                        .orderId(order.getOrderId())
                        .orderStatus(order.getOrderStatus())
                        .items(order.getCollectionOfItems().stream().map(orderItem -> OrderItemResponse.builder()
                                        .itemId(orderItem.getItemId())
                                        .itemName(orderItem.getItemName())
                                        .quantity(orderItem.getItemQuantity())
                                        .itemUnitPrice(orderItem.getItemUnitPrice())
                                        .build())
                                .toList())
                        .build())
                .toList();
    }

    public OrderResponse getOrderById(Long orderId) {
        var optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) return null;

        var order = optionalOrder.get();

        return OrderResponse.builder()
                .orderDate(order.getOrderDate())
                .orderId(order.getOrderId())
                .orderStatus(order.getOrderStatus())
                .items(order.getCollectionOfItems().stream().map(orderItem -> OrderItemResponse.builder()
                                .itemId(orderItem.getItemId())
                                .itemName(orderItem.getItemName())
                                .quantity(orderItem.getItemQuantity())
                                .itemUnitPrice(orderItem.getItemUnitPrice())
                                .build())
                        .toList())
                .build();
    }
}
