package com.chaupez.spring.webflux.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.chaupez.spring.webflux.dto.NotaDto;
import com.chaupez.spring.webflux.entity.Nota;
import com.chaupez.spring.webflux.repository.NotaRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NotaService {

    private final NotaRepository notaRepository;

    public Flux<Nota> getAllNotas() {
        return notaRepository.findAll();
    }

    public Flux<Nota> getNotasByUsuarioId(Integer idUsuario) {

        return notaRepository.findByIdUsuario(idUsuario);
    }

    public Mono<Nota> getNotaById(Integer idNota) {
        return notaRepository.findById(idNota);
    }

    public Mono<Nota> saveNota(NotaDto dto) {
        Nota nota = new Nota();
        nota.setIdUsuario(dto.getIdUsuario());
        nota.setTitulo(dto.getTitulo());
        nota.setContenido(dto.getContenido());
        nota.setFechaCreacion(LocalDateTime.now());
        return notaRepository.save(nota);
    }
    
    public Mono<Nota> updateNota(Integer idNota, NotaDto dto) {
        return notaRepository.findById(idNota)
                .flatMap(existingNota -> {
                    existingNota.setTitulo(dto.getTitulo() != null ? dto.getTitulo() : existingNota.getTitulo());
                    existingNota.setContenido(dto.getContenido() != null ? dto.getContenido() : existingNota.getContenido());
                    existingNota.setIdUsuario(dto.getIdUsuario() != null ? dto.getIdUsuario() : existingNota.getIdUsuario());
                    existingNota.setFechaActualizacion(LocalDateTime.now());
                    return notaRepository.save(existingNota);
                });
    }
    
    
    
    public Mono<Void> deleteNota(Integer idNota) {
        return notaRepository.deleteById(idNota);
    }
}
