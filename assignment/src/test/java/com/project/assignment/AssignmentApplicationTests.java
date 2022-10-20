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
import java.util.Optional;

@SpringBootTest
class AssignmentApplicationTests {

	@Autowired
	private OrderRepository orderRepository;

	@Test
	void createNewOrder() {

		var order = Order.builder()
				.orderDate(LocalDate.now())
				.orderStatus("New")
				.collectionOfItems(List.of(OrderItem.builder()
						.itemName("item 1")
						.itemQuantity(1L)
						.itemUnitPrice(BigDecimal.ONE)
						.build(), OrderItem.builder()
						.itemName("item 2")
						.itemQuantity(2L)
						.itemUnitPrice(BigDecimal.TEN)
						.build()))
				.build();

		orderRepository.save(order);

		Optional<Order> dbOrder = orderRepository.findById(order.getOrderId());

		assert dbOrder.isPresent();
		assert dbOrder.get().getOrderDate().isEqual(order.getOrderDate());
		assert dbOrder.get().getCollectionOfItems().size() == 2;
		assert dbOrder.get().getCollectionOfItems().get(0).getItemName().equals("item 1");
		assert dbOrder.get().getCollectionOfItems().get(1).getItemName().equals("item 2");

		orderRepository.delete(order);
	}
}
