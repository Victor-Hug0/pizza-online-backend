package com.pizzaonline.PizzaOnline.domain.cliente;

import java.util.UUID;

public record ClienteResponseDTO(UUID id, String name, String email) {

    public ClienteResponseDTO(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
