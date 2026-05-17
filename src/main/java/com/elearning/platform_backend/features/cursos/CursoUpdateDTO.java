package com.elearning.platform_backend.features.cursos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record CursoUpdateDTO (
    @NotBlank String titulo,
    @NotBlank String descripcion,
    @JsonProperty("id_docente") Long docenteID
) {}
