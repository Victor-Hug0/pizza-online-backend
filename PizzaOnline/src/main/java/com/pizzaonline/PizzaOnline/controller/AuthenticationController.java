package com.pizzaonline.PizzaOnline.controller;

import com.pizzaonline.PizzaOnline.domain.cliente.AuthenticationDTO;
import com.pizzaonline.PizzaOnline.domain.cliente.Cliente;
import com.pizzaonline.PizzaOnline.domain.cliente.LoginResponseDTO;
import com.pizzaonline.PizzaOnline.domain.cliente.RegisterDTO;
import com.pizzaonline.PizzaOnline.infra.security.TokenService;
import com.pizzaonline.PizzaOnline.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Cliente) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data){
        if (this.clienteRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        Cliente cliente = new Cliente(data.name(), data.phoneNumber(), data.email(), encryptedPassword, data.role());

        this.clienteRepository.save(cliente);

        return ResponseEntity.ok().body(cliente);
    }
}
