package com.elearning.platform_backend.features.evaluaciones;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EvaluacionUpdateDTO(

    @NotBlank
    String titulo,

    @NotNull
    TipoEvaluacion tipo,

    @NotBlank
    String descripcion,

    @NotNull
    @JsonProperty("puntaje_minimo") Integer puntajeMinimo
) {}
