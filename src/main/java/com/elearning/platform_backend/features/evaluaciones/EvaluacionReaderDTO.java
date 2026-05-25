package com.elearning.platform_backend.features.evaluaciones;

import java.util.List;

public record EvaluacionReaderDTO(
    Long id,
    String titulo,
    TipoEvaluacion tipo,
    String descripcion,
    Long cursoId,
    Integer puntajeMinimo,
    List<Long> preguntas
) {}
