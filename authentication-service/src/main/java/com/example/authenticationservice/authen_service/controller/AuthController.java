package com.example.authenticationservice.authen_service.controller;

import com.example.authenticationservice.account.repository.AccountService;
import com.example.authenticationservice.authen_service.entities.AuthRequest;
import com.example.authenticationservice.authen_service.entities.AuthResponse;
import com.example.authenticationservice.authen_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;
    private final AccountService accountService;

    @Autowired
    public AuthController(final AuthService authService, final AccountService accountService) {
        this.authService = authService;
        this.accountService = accountService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.logIn(authRequest, accountService.getAccountAuth(authRequest.getName())));
    }
}