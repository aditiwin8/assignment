package com.project.assignment.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private String orderStatus;
    private LocalDate orderDate;
    private List<OrderItemResponse> items;
}
