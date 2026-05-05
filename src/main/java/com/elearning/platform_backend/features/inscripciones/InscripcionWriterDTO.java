package com.elearning.platform_backend.features.inscripciones;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InscripcionWriterDTO(
    @NotNull @JsonProperty("estudiante_id") Long estudianteId,
    @NotNull @JsonProperty("curso_id") Long cursoId,
    @NotBlank String estado
) {}