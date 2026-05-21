package com.elearning.platform_backend.features.evaluaciones;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EvaluacionWriterDTO(

    @NotBlank
    String titulo,

    @NotNull
    TipoEvaluacion tipo,

    @NotBlank
    String descripcion,

    @NotNull
    @JsonProperty("curso_id") Long cursoId,

    @NotNull
    @JsonProperty("puntaje_minimo") Integer puntajeMinimo
) {}
