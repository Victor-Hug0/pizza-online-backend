package com.pizzaonline.PizzaOnline.domain.cliente;

public record RegisterDTO(String name, String phoneNumber, String email, String password, UserRole role) {
}
