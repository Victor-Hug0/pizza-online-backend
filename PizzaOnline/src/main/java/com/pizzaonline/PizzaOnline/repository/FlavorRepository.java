package com.pizzaonline.PizzaOnline.repository;

import com.pizzaonline.PizzaOnline.domain.flavor.Flavor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlavorRepository extends JpaRepository<Flavor, Long> {

    Flavor findByName(String name);
}
