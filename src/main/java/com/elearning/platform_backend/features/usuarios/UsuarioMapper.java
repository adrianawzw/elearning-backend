package com.elearning.platform_backend.features.usuarios;

import com.elearning.platform_backend.features.usuarios.docentes.Docente;
import com.elearning.platform_backend.features.usuarios.estudiantes.Estudiante;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioWriterDTO dto) {
        return Usuario.builder()
                .email(dto.email())
                .password(dto.password())
                .rol(dto.rol())
                .build();
    }

    public static UsuarioReaderDTO toDto(Usuario user, Docente docente, Estudiante estudiante) {
        String nombres = "";
        String apellidos = "";
        String especialidad = null;
        String codigoAlumno = null;

        if (docente != null) {
            nombres = docente.getNombres();
            apellidos = docente.getApellidos();
            especialidad = docente.getEspecialidad();
        } else if (estudiante != null) {
            nombres = estudiante.getNombres();
            apellidos = estudiante.getApellidos();
            codigoAlumno = estudiante.getCodigoAlumno();
        }

        return new UsuarioReaderDTO(
                user.getId(),
                user.getEmail(),
                nombres,
                apellidos,
                user.getRol(),
                user.getFechaRegistro(),
                especialidad,
                codigoAlumno
        );
    }

    public static UsuarioReaderDTO tDto(Docente docente) {
        return new UsuarioReaderDTO(
                docente.getUsuario().getId(),
                docente.getUsuario().getEmail(),
                docente.getNombres(),
                docente.getApellidos(),
                docente.getUsuario().getRol(),
                docente.getUsuario().getFechaRegistro(),
                docente.getEspecialidad(),
                null
        );
    }
}
