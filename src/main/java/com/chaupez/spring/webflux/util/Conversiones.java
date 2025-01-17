package com.chaupez.spring.webflux.util;

import com.chaupez.spring.webflux.dto.NotaResponseDto;
import com.chaupez.spring.webflux.entity.Nota;
import java.time.format.DateTimeFormatter;

public class Conversiones {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static NotaResponseDto toDto(Nota nota) {
        NotaResponseDto dto = new NotaResponseDto();
        dto.setIdNota(nota.getIdNota());
        dto.setTitulo(nota.getTitulo());
        dto.setContenido(nota.getContenido());
        dto.setFechaCreacion(nota.getFechaCreacion() != null ? formatter.format(nota.getFechaCreacion()) : null);
        dto.setFechaActualizacion(nota.getFechaActualizacion() != null ? formatter.format(nota.getFechaActualizacion()) : null);
        dto.setIdUsuario(nota.getIdUsuario());
        return dto;
    }
}
