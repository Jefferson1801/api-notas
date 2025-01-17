package com.chaupez.spring.webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.chaupez.spring.webflux.entity.Nota;

import reactor.core.publisher.Flux;

@Repository
public interface NotaRepository extends ReactiveCrudRepository<Nota, Integer> {
    Flux<Nota> findByIdUsuario(Integer idUsuario);
}

