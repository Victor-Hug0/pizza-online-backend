package com.pizzaonline.PizzaOnline.repository;

import com.pizzaonline.PizzaOnline.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
