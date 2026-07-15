package com.elearning.platform_backend.features.cursos;

import java.util.List;

import com.elearning.platform_backend.features.cursos.contenidos.Contenido;
import com.elearning.platform_backend.features.evaluaciones.Evaluacion;
import com.elearning.platform_backend.features.inscripciones.Inscripcion;
import com.elearning.platform_backend.features.usuarios.UsuarioMapper;
import com.elearning.platform_backend.features.usuarios.docentes.Docente;

public class CursoMapper {
    
    // Para el POST (Registro) - Convierte DTO a la entidad base Curso
    public static Curso toEntity(CursoWriterDTO dto, Docente docente){
        return Curso.builder()
                .titulo(dto.titulo())
                .descripcion(dto.descripcion())
                .nivel(dto.nivel())
                .duracion(dto.duracion())
                .categoria(dto.categoria())
                .imagenUrl(dto.imagenUrl())
                .docente(docente)
                .build();
    }

    // Para el GET (Lectura) - Obtiene los datos de la entidad Curso y los convierte a DTO
    public static CursoReaderDTO toDto(Curso curso) {
        /*return new CursoReaderDTO(
                curso.getId(),
                curso.getTitulo(),
                curso.getDescripcion(),
                UsuarioMapper.tDto(curso.getDocente()),
                curso.getFechaCreacion(),
                curso.getContenidos().stream().map(Contenido::getId).toList(),
                curso.getEvaluaciones().stream().map(Evaluacion::getId).toList(),
                curso.getInscripciones().stream().map(Inscripcion::getId).toList()
        );*/
        return new CursoReaderDTO(
                curso.getId(),
                curso.getTitulo(),
                curso.getDescripcion(),
                curso.getNivel(),
                curso.getDuracion(),
                curso.getCategoria(),
                curso.getImagenUrl(),
                UsuarioMapper.tDto(curso.getDocente()),
                curso.getFechaCreacion(),
                curso.getContenidos() == null ? List.of() :
                    curso.getContenidos().stream().map(Contenido::getId).toList(),
                curso.getEvaluaciones() == null ? List.of() :
                    curso.getEvaluaciones().stream().map(Evaluacion::getId).toList(),
                curso.getInscripciones() == null ? List.of() :
                    curso.getInscripciones().stream().map(Inscripcion::getId).toList()
        );
    }
}
