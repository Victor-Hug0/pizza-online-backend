package com.pizzaonline.PizzaOnline.domain.order;

import com.pizzaonline.PizzaOnline.domain.cliente.ClienteResponseDTO;
import com.pizzaonline.PizzaOnline.domain.pizza.PizzaResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(Long id, double totalValue, LocalDateTime orderDate, ClienteResponseDTO clienteResponseDTO, List<PizzaResponseDTO> pizzaResponseDTOS) {
    public OrderResponseDTO(Long id, double totalValue, LocalDateTime orderDate, ClienteResponseDTO clienteResponseDTO, List<PizzaResponseDTO> pizzaResponseDTOS) {
        this.id = id;
        this.totalValue = totalValue;
        this.orderDate = orderDate;
        this.clienteResponseDTO = clienteResponseDTO;
        this.pizzaResponseDTOS = pizzaResponseDTOS;
    }
}
