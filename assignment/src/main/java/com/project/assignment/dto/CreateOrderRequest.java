package com.project.assignment.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private LocalDate orderDate;
    private List<CreateOrderItem> items;
}
