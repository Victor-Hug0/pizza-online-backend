package com.pizzaonline.PizzaOnline.controller;

import com.pizzaonline.PizzaOnline.domain.flavor.Flavor;
import com.pizzaonline.PizzaOnline.domain.flavor.FlavorRequestDTO;
import com.pizzaonline.PizzaOnline.repository.FlavorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flavors")
public class FlavorController {

    @Autowired
    private final FlavorRepository flavorRepository;

    public FlavorController(FlavorRepository flavorRepository) {
        this.flavorRepository = flavorRepository;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody FlavorRequestDTO data){
        if (flavorRepository.findByName(data.name()) != null) return ResponseEntity.badRequest().body("Sabor já registrado");
        Flavor flavor = new Flavor(data.name());
        flavorRepository.save(flavor);
        return ResponseEntity.ok().body(flavor);
    }

    @GetMapping
    public List<Flavor> getAll(){
        return flavorRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(flavorRepository.findById(id).isEmpty()) {
            return ResponseEntity.badRequest().body("Sabor não encontrado");
        }

        flavorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
