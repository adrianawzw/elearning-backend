package com.elearning.platform_backend.features.usuarios;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioWriterDTO(
    @NotBlank 
    @Email 
    String email,

    @NotBlank 
    @Size(min = 8, max = 15) 
    String password,

    @NotBlank 
    @Size(max = 100) 
    String nombres,

    @NotBlank 
    @Size(max = 100) 
    String apellidos,

    @NotNull 
    Rol rol,

    String especialidad, 
    @JsonProperty("codigo_alumno") String codigoAlumno
) {}
