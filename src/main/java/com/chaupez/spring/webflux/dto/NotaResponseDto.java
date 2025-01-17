package com.chaupez.spring.webflux.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotaResponseDto {
    private Integer idNota;
    private String titulo;
    private String contenido;
    private String fechaCreacion; 
    private String fechaActualizacion;
    private Integer idUsuario;
}
