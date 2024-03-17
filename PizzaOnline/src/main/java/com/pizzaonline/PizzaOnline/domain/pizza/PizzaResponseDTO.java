package com.pizzaonline.PizzaOnline.domain.pizza;

import com.pizzaonline.PizzaOnline.domain.flavor.Flavor;

import java.util.List;

public record PizzaResponseDTO(Long id, String name, double price, List<Flavor> flavors) {
    public PizzaResponseDTO(Long id, String name, double price, List<Flavor> flavors) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.flavors = flavors;
    }
}
