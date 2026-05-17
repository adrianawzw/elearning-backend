package com.elearning.platform_backend.features.cursos;

import java.time.LocalDateTime;
import java.util.List;

import com.elearning.platform_backend.features.usuarios.UsuarioReaderDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CursoReaderDTO (
    Long id,
    String titulo,
    String descripcion,
    UsuarioReaderDTO docente,
    
    @JsonProperty("fecha_creacion") LocalDateTime fechaCreacion,
    
    List<Long> contenido,
    List<Long> evaluaciones,
    List<Long> inscripciones

) {}
