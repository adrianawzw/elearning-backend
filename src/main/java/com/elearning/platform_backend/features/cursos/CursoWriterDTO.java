package com.elearning.platform_backend.features.cursos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CursoWriterDTO (
    
    @NotBlank
    @Size(min = 3, max = 50)
    String titulo,

    @NotBlank
    @Size(max = 250)
    String descripcion,

    String nivel,
    String duracion,
    String categoria,

    @JsonProperty("imagen_url")
    String imagenUrl,

    @NotNull
    @JsonProperty("id_docente") Long docenteID
) {}
