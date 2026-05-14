package com.elearning.platform_backend.features.usuarios;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UsuarioReaderDTO(Long id,
    String email,
    String nombres,
    String apellidos,
    Rol rol,
    @JsonProperty("fecha_registro") LocalDateTime fechaRegistro,
    
    String especialidad,
    @JsonProperty("codigo_alumno") String codigoAlumno
) {}
