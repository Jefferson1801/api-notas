package com.chaupez.spring.webflux.dto;

import lombok.Data;

@Data
public class NotaDto {
    private String titulo;
    private String contenido;
    private Integer idUsuario;
}
