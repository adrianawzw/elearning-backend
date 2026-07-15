package com.elearning.platform_backend.features.cursos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record CursoUpdateDTO (
    @NotBlank String titulo,
    @NotBlank String descripcion,
    String nivel,
    String duracion,
    String categoria,
    @JsonProperty("imagen_url") String imagenUrl,
    @JsonProperty("id_docente") Long docenteID
) {}
