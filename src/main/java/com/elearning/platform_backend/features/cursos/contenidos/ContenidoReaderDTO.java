package com.elearning.platform_backend.features.cursos.contenidos;

public record ContenidoReaderDTO(
    Long id,
    String titulo,
    String urlMaterial,
    String tipo,
    Long cursoId
) {}
