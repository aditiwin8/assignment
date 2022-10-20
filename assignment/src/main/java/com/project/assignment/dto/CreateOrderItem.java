package com.project.assignment.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItem {
    private String itemName;
    private BigDecimal itemUnitPrice;
    private Long itemQuantity;
}
