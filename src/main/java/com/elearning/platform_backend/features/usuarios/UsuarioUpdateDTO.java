package com.elearning.platform_backend.features.usuarios;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record UsuarioUpdateDTO(
    @NotBlank String nombres,
    @NotBlank String apellidos,
    String especialidad,
    @JsonProperty("codigo_alumno") String codigoAlumno
) {}