package com.elearning.platform_backend.features.inscripciones;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InscripcionReaderDTO(
        Long id,
        @JsonProperty("estudiante_id") Long estudianteId,
        @JsonProperty("estudiante_nombre") String estudianteNombre,
        @JsonProperty("curso_id") Long cursoId,
        @JsonProperty("curso_titulo") String cursoTitulo,
        @JsonProperty("fecha_inscripcion") LocalDate fechaInscripcion,
        String estado) {
}
