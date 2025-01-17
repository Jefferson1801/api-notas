package com.chaupez.spring.webflux.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chaupez.spring.webflux.dto.NotaDto;
import com.chaupez.spring.webflux.model.HttpResult;
import com.chaupez.spring.webflux.service.NotaService;
import com.chaupez.spring.webflux.util.Conversiones;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;


    @GetMapping("/usuario")
    public Mono<HttpResult> obtenerNotasPorUsuario(
            @RequestParam(value = "idUsuario", required = false) Integer idUsuario) {
        return (idUsuario != null ? notaService.getNotasByUsuarioId(idUsuario) : notaService.getAllNotas())
                .map(Conversiones::toDto)
                .collectList()
                .map(notas -> new HttpResult(200, "ok", notas))
                .onErrorResume(e -> Mono.just(new HttpResult(500, "error", e.getMessage())));
    }

    @GetMapping("/{idNota}")
    public Mono<HttpResult> obtenerNotaPorId(@PathVariable Integer idNota) {
        return notaService.getNotaById(idNota)
                .map(Conversiones::toDto)
                .map(nota -> new HttpResult(200, "ok", nota))
                .switchIfEmpty(Mono.just(new HttpResult(404, "Nota no encontrada", null)))
                .onErrorResume(e -> Mono.just(new HttpResult(500, "error", e.getMessage())));
    }

    @PostMapping
    public Mono<HttpResult> crearNota(@RequestBody NotaDto notaDto) {
        return notaService.saveNota(notaDto)
                .map(Conversiones::toDto)
                .map(nota -> new HttpResult(201, "Nota creada", nota))
                .onErrorResume(e -> Mono.just(new HttpResult(500, "error", e.getMessage())));
    }

    @PutMapping("/{idNota}")
    public Mono<HttpResult> actualizarNota(@PathVariable Integer idNota, @RequestBody NotaDto notaDto) {
        return notaService.updateNota(idNota, notaDto)
                .map(Conversiones::toDto)
                .map(nota -> new HttpResult(200, "Nota actualizada", nota))
                .switchIfEmpty(Mono.just(new HttpResult(404, "Nota no encontrada", null)))
                .onErrorResume(e -> Mono.just(new HttpResult(500, "error", e.getMessage())));
    }

    @DeleteMapping("/{idNota}")
    public Mono<HttpResult> eliminarNota(@PathVariable Integer idNota) {
        return notaService.deleteNota(idNota)
                .then(Mono.just(new HttpResult(200, "Nota eliminada", null)))
                .onErrorResume(e -> Mono.just(new HttpResult(500, "error", e.getMessage())));
    }

}
