package com.chaupez.spring.webflux.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chaupez.spring.webflux.config.TokenProvider;
 
import com.chaupez.spring.webflux.repository.UserDetailsRepository;
import com.chaupez.spring.webflux.util.Constantes;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import com.chaupez.spring.webflux.controller.LoginResponse;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public Mono<LoginResponse> authenticate(String username, String password) {
        return userDetailsRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(tokenProvider::generateToken)
                .map(token -> new LoginResponse(token, true, Constantes.MENSAJE_INICIAR_SESION) );
                
    }
}
