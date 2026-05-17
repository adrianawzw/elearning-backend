package com.elearning.platform_backend.features.cursos;

import com.elearning.platform_backend.features.evaluaciones.Evaluacion;
import com.elearning.platform_backend.features.inscripciones.Inscripcion;
import com.elearning.platform_backend.features.usuarios.Docente;
import com.elearning.platform_backend.features.usuarios.UsuarioMapper;

public class CursoMapper {
    
    // Para el POST (Registro) - Convierte DTO a la entidad base Curso
    public static Curso toEntity(CursoWriterDTO dto, Docente docente){
        return Curso.builder()
                .titulo(dto.titulo())
                .descripcion(dto.descripcion())
                .docente(docente).
                build();
    }

    // Para el GET (Lectura) - Obtiene los datos de la entidad Curso y los convierte a DTO
    public static CursoReaderDTO toDto(Curso curso) {
        return new CursoReaderDTO(
                curso.getId(),
                curso.getTitulo(),
                curso.getDescripcion(),
                UsuarioMapper.tDto(curso.getDocente()),
                curso.getFechaCreacion(),
                curso.getContenidos().stream().map(Contenido::getId).toList(),
                curso.getEvaluaciones().stream().map(Evaluacion::getId).toList(),
                curso.getInscripciones().stream().map(Inscripcion::getId).toList()
        );
    }
}
