package com.pizzaonline.PizzaOnline.domain.pizza;

import java.util.List;

public record PizzaRequestDTO(String name, Double price, PizzaType pizzaType, List<Long> flavorIds) {
}
