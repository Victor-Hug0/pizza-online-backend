package com.pizzaonline.PizzaOnline.controller;

import com.pizzaonline.PizzaOnline.domain.cliente.Cliente;
import com.pizzaonline.PizzaOnline.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getAll(){
        return clienteRepository.findAll();
    }
}
