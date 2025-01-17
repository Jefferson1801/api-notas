package com.chaupez.spring.webflux.service;

import com.chaupez.spring.webflux.dto.NotaDto;
import com.chaupez.spring.webflux.entity.Nota;
import com.chaupez.spring.webflux.repository.NotaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.*;

class NotaServiceTest {

    @InjectMocks
    private NotaService notaService;

    @Mock
    private NotaRepository notaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllNotas() {
        Nota nota1 = new Nota();
        nota1.setIdNota(1);
        nota1.setTitulo("Nota 1");
        nota1.setContenido("Contenido 1");

        Nota nota2 = new Nota();
        nota2.setIdNota(2);
        nota2.setTitulo("Nota 2");
        nota2.setContenido("Contenido 2");

        when(notaRepository.findAll()).thenReturn(Flux.fromIterable(Arrays.asList(nota1, nota2)));

        Flux<Nota> result = notaService.getAllNotas();

        StepVerifier.create(result)
                .expectNext(nota1, nota2)
                .verifyComplete();

        verify(notaRepository, times(1)).findAll();
    }

    @Test
    void testGetNotasByUsuarioId() {
        Integer userId = 1;
        Nota nota = new Nota();
        nota.setIdNota(1);
        nota.setIdUsuario(userId);
        nota.setTitulo("Nota del usuario 1");

        when(notaRepository.findByIdUsuario(userId)).thenReturn(Flux.just(nota));

        Flux<Nota> result = notaService.getNotasByUsuarioId(userId);

        StepVerifier.create(result)
                .expectNext(nota)
                .verifyComplete();

        verify(notaRepository, times(1)).findByIdUsuario(userId);
    }

    @Test
    void testGetNotaById() {
        Integer notaId = 1;
        Nota nota = new Nota();
        nota.setIdNota(notaId);
        nota.setTitulo("Nota 1");

        when(notaRepository.findById(notaId)).thenReturn(Mono.just(nota));

        Mono<Nota> result = notaService.getNotaById(notaId);

        StepVerifier.create(result)
                .expectNext(nota)
                .verifyComplete();

        verify(notaRepository, times(1)).findById(notaId);
    }

    @Test
    void testSaveNota() {
        NotaDto dto = new NotaDto();
        dto.setTitulo("Nueva Nota");
        dto.setContenido("Contenido de la nueva nota");
        dto.setIdUsuario(1);

        Nota nota = new Nota();
        nota.setIdNota(1);
        nota.setTitulo(dto.getTitulo());
        nota.setContenido(dto.getContenido());
        nota.setIdUsuario(dto.getIdUsuario());

        when(notaRepository.save(any(Nota.class))).thenReturn(Mono.just(nota));

        Mono<Nota> result = notaService.saveNota(dto);

        StepVerifier.create(result)
                .expectNextMatches(
                        savedNota -> savedNota.getTitulo().equals("Nueva Nota") && savedNota.getIdUsuario() == 1)
                .verifyComplete();

        verify(notaRepository, times(1)).save(any(Nota.class));
    }

    @Test
    void testUpdateNota() {
        Integer notaId = 1;

        Nota existingNota = new Nota();
        existingNota.setIdNota(notaId);
        existingNota.setTitulo("Titulo Original");
        existingNota.setContenido("Contenido Original");
        existingNota.setIdUsuario(1);

        NotaDto dto = new NotaDto();
        dto.setTitulo("Titulo Actualizado");
        dto.setContenido("Contenido Actualizado");
        dto.setIdUsuario(2);

        Nota updatedNota = new Nota();
        updatedNota.setIdNota(notaId);
        updatedNota.setTitulo(dto.getTitulo());
        updatedNota.setContenido(dto.getContenido());
        updatedNota.setIdUsuario(dto.getIdUsuario());
        updatedNota.setFechaActualizacion(LocalDateTime.now());

        when(notaRepository.findById(notaId)).thenReturn(Mono.just(existingNota));
        when(notaRepository.save(any(Nota.class))).thenReturn(Mono.just(updatedNota));

        Mono<Nota> result = notaService.updateNota(notaId, dto);

        StepVerifier.create(result)
                .expectNextMatches(
                        updated -> updated.getTitulo().equals("Titulo Actualizado") && updated.getIdUsuario() == 2)
                .verifyComplete();

        verify(notaRepository, times(1)).findById(notaId);
        verify(notaRepository, times(1)).save(any(Nota.class));
    }

    @Test
    void testDeleteNota() {
        Integer notaId = 1;

        when(notaRepository.deleteById(notaId)).thenReturn(Mono.empty());

        Mono<Void> result = notaService.deleteNota(notaId);

        StepVerifier.create(result)
                .verifyComplete();

        verify(notaRepository, times(1)).deleteById(notaId);
    }
}
