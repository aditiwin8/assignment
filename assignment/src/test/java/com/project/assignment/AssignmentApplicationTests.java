package com.project.assignment;

import com.project.assignment.data.entity.Order;
import com.project.assignment.data.entity.OrderItem;
import com.project.assignment.data.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class AssignmentApplicationTests {

	@Autowired
	private final OrderRepository orderRepository;

	AssignmentApplicationTests(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Test
	void createNewOrder() {
		var order = Order.builder()
				.orderDate(LocalDate.now())
				.orderStatus("New")
				.collectionOfItems(List.of(OrderItem.builder()
						.itemName("1tem 1")
						.itemQuantity(1L)
						.itemUnitPrice(BigDecimal.ONE)
						.build(), OrderItem.builder()
						.itemName("1tem 2")
						.itemQuantity(2L)
						.itemUnitPrice(BigDecimal.TEN)
						.build()))
				.build();

		orderRepository.save(order);
	}
}
