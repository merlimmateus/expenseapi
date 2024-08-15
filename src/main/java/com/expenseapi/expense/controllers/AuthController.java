package com.expenseapi.expense.controllers;

import com.expenseapi.expense.dto.LoginResponseDTO;
import com.expenseapi.expense.dto.UserDTO;
import com.expenseapi.expense.models.User;
import com.expenseapi.expense.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody User user) {
        UserDTO registeredUserDTO = authService.register(user);
        return ResponseEntity.ok(registeredUserDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestParam String email, @RequestParam String password) {
        LoginResponseDTO response = authService.login(email, password);
        return ResponseEntity.ok(response);
    }
}