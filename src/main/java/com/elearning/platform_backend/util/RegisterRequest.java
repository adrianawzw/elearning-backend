package com.elearning.platform_backend.util;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

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
    String apellidos

) {}