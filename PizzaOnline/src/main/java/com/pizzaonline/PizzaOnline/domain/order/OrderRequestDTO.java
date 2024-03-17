package com.pizzaonline.PizzaOnline.domain.order;

import com.pizzaonline.PizzaOnline.domain.cliente.Cliente;
import com.pizzaonline.PizzaOnline.domain.pizza.Pizza;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderRequestDTO(List<Long> pizzaIds) {
}
