package com.elearning.platform_backend.features.cursos.contenidos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContenidoWriterDTO(

    @NotBlank
    String titulo,

    @NotBlank
    @JsonProperty("url_material") String urlMaterial,

    @NotBlank
    String tipo,

    @NotNull
    @JsonProperty("curso_id") Long cursoId
) {}
