package com.chaupez.spring.webflux.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

 

import com.chaupez.spring.webflux.service.LoginService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/iniciar")
    public Mono<LoginResponse> login(@RequestBody Map<String, String> user) {
        return loginService.authenticate(user.get("username"), user.get("password"))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
    }



 
 

 
}