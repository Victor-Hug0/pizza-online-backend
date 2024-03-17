package com.pizzaonline.PizzaOnline.controller;

import com.pizzaonline.PizzaOnline.domain.flavor.Flavor;
import com.pizzaonline.PizzaOnline.domain.pizza.Pizza;
import com.pizzaonline.PizzaOnline.domain.pizza.PizzaRequestDTO;
import com.pizzaonline.PizzaOnline.repository.FlavorRepository;
import com.pizzaonline.PizzaOnline.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private final PizzaRepository  pizzaRepository;
    @Autowired
    private final FlavorRepository flavorRepository;

    public PizzaController(PizzaRepository pizzaRepository, FlavorRepository flavorRepository) {
        this.pizzaRepository = pizzaRepository;
        this.flavorRepository = flavorRepository;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody PizzaRequestDTO data){
        List<Flavor> flavors = flavorRepository.findAllById(data.flavorIds());
        Pizza pizza = new Pizza(data.name(), data.price(), data.pizzaType(), flavors);

        pizzaRepository.save(pizza);

        return ResponseEntity.ok().body(pizza);
    }
}
