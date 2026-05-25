package com.elearning.platform_backend.features.inscripciones;

import java.time.LocalDate;

public class InscripcionMapper {

    public static InscripcionReaderDTO toDto(Inscripcion inscripcion) {
        String estudianteNombre = inscripcion.getEstudiante() != null
                ? inscripcion.getEstudiante().getNombres() + " " + inscripcion.getEstudiante().getApellidos()
                : null;
        String cursoTitulo = inscripcion.getCurso() != null ? inscripcion.getCurso().getTitulo() : null;
        LocalDate fechaInscripcion = inscripcion.getFechaInscripcion() != null
                ? inscripcion.getFechaInscripcion().toLocalDate()
                : null;

        return new InscripcionReaderDTO(
                inscripcion.getId(),
                inscripcion.getEstudiante() != null ? inscripcion.getEstudiante().getId() : null,
                estudianteNombre,
                inscripcion.getCurso() != null ? inscripcion.getCurso().getId() : null,
                cursoTitulo,
                fechaInscripcion,
                inscripcion.getEstado());
    }
}
