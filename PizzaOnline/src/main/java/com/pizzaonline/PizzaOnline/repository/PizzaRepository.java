package com.pizzaonline.PizzaOnline.repository;

import com.pizzaonline.PizzaOnline.domain.pizza.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
