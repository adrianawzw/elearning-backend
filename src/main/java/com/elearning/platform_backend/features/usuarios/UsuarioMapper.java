package com.elearning.platform_backend.features.usuarios;

public class UsuarioMapper {
    // Para el POST (Registro) - Convierte DTO a la entidad base Usuario
    public static Usuario toEntity(UsuarioWriterDTO dto) {
        return Usuario.builder()
                .email(dto.email())
                .password(dto.password())
                .rol(dto.rol())
                .build();
    }

    // Para el GET (Lectura) - Combina Usuario y Perfil en un solo DTO
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
}
