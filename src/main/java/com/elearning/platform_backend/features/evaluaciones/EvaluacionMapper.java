package com.elearning.platform_backend.features.evaluaciones;

import java.util.List;

import com.elearning.platform_backend.features.cursos.Curso;
import com.elearning.platform_backend.features.evaluaciones.preguntas.Pregunta;

public class EvaluacionMapper {

    public static Evaluacion toEntity(EvaluacionWriterDTO dto, Curso curso) {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setTitulo(dto.titulo());
        evaluacion.setTipo(dto.tipo());
        evaluacion.setDescripcion(dto.descripcion());
        evaluacion.setCurso(curso);
        evaluacion.setPuntajeMinimo(dto.puntajeMinimo());
        return evaluacion;
    }

    public static EvaluacionReaderDTO toDto(Evaluacion evaluacion) {
        List<Long> preguntaIds = evaluacion.getPreguntas() == null ? List.of()
                : evaluacion.getPreguntas().stream().map(Pregunta::getId).toList();

        return new EvaluacionReaderDTO(
            evaluacion.getId(),
            evaluacion.getTitulo(),
            evaluacion.getTipo(),
            evaluacion.getDescripcion(),
            evaluacion.getCurso() != null ? evaluacion.getCurso().getId() : null,
            evaluacion.getPuntajeMinimo(),
            preguntaIds
        );
    }
}
