package com.pizzaonline.PizzaOnline.controller;

import com.pizzaonline.PizzaOnline.domain.cliente.Cliente;
import com.pizzaonline.PizzaOnline.domain.cliente.ClienteResponseDTO;
import com.pizzaonline.PizzaOnline.domain.order.Order;
import com.pizzaonline.PizzaOnline.domain.order.OrderRequestDTO;
import com.pizzaonline.PizzaOnline.domain.order.OrderResponseDTO;
import com.pizzaonline.PizzaOnline.domain.pizza.Pizza;
import com.pizzaonline.PizzaOnline.domain.pizza.PizzaResponseDTO;
import com.pizzaonline.PizzaOnline.repository.ClienteRepository;
import com.pizzaonline.PizzaOnline.repository.OrderRepository;
import com.pizzaonline.PizzaOnline.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final PizzaRepository pizzaRepository;
    @Autowired
    private final ClienteRepository clienteRepository;

    public OrderController(OrderRepository orderRepository, PizzaRepository pizzaRepository, ClienteRepository clienteRepository) {
        this.orderRepository = orderRepository;
        this.pizzaRepository = pizzaRepository;
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody OrderRequestDTO data){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Cliente cliente = clienteRepository.findClienteByEmail(email);
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        List<Pizza> pizzas = pizzaRepository.findAllById(data.pizzaIds());
        if (pizzas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhuma pizza encontrada");
        }

        double totalValue = pizzas.stream().mapToDouble(Pizza::getPrice).sum();

        Order order = new Order(totalValue, LocalDateTime.now(), cliente, pizzas);
        orderRepository.save(order);

        ClienteResponseDTO clienteDTO = new ClienteResponseDTO(cliente.getId(), cliente.getName(), cliente.getEmail());
        List<PizzaResponseDTO> pizzasDTO = pizzas.stream()
                .map(pizza -> new PizzaResponseDTO(pizza.getId(), pizza.getName(), pizza.getPrice(), pizza.getFlavors()))
                .collect(Collectors.toList());

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO(order.getId(), order.getTotalValue(),
                order.getOrderDate(), clienteDTO, pizzasDTO);

        return ResponseEntity.ok().body(orderResponseDTO);
    }

    @GetMapping("/cliente")
    public List<OrderResponseDTO> getALl(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Cliente cliente = clienteRepository.findClienteByEmail(email);
        List<Order> orders = cliente.getOrders();

        return orders.stream()
                .map(order -> {
                    ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(order.getCliente().getId(), order.getCliente().getName(), order.getCliente().getEmail());
                    List<PizzaResponseDTO> pizzaResponseDTOS = order.getPizzas().stream()
                            .map(pizza -> new PizzaResponseDTO(pizza.getId(), pizza.getName(), pizza.getPrice(), pizza.getFlavors())).toList();
                    return new OrderResponseDTO(order.getId(), order.getTotalValue(), order.getOrderDate(), clienteResponseDTO, pizzaResponseDTOS);
                }).toList();
    }
}
