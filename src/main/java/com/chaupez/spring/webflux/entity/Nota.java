package com.chaupez.spring.webflux.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "NOTAS")
@Getter
@Setter
public class Nota {
    @Id
    @Column("ID_NOTA")
    private Integer idNota;

    @Column("TITULO")
    private String titulo;

    @Column("CONTENIDO")
    private String contenido;

    @Column("FECHA_CREACION")
    private LocalDateTime fechaCreacion;

    @Column("FECHA_ACTUALIZACION")
    private LocalDateTime fechaActualizacion;

    @Column("ID_USUARIO")
    private Integer idUsuario;
}
