package com.elearning.platform_backend.features.cursos.contenidos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record ContenidoUpdateDTO(

    @NotBlank
    String titulo,

    @NotBlank
    @JsonProperty("url_material") String urlMaterial,

    @NotBlank
    String tipo
) {}
