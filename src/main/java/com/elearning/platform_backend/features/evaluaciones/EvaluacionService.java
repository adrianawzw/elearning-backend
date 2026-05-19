package com.elearning.platform_backend.features.evaluaciones;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.elearning.platform_backend.features.cursos.Curso;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluacionService {
    private final EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> listar() {
        return evaluacionRepository.findAll();
    }

    public Evaluacion guardar(Evaluacion e) {
        return evaluacionRepository.save(e);
    }

    public Evaluacion buscarPorId(Long id) {
        return evaluacionRepository.findById(id)
                .orElseThrow();
    }

    // Operaciones de lectura en tablas Relacionadas (2)
    public List<Evaluacion> buscarPorCurso(Long cursoId) {
        List<Evaluacion> evaluaciones = evaluacionRepository.findByCursoId(cursoId);

        if (evaluaciones.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT,
                    "No hay evaluaciones para este curso");
        }

        return evaluaciones;
    }

    public void eliminar(Long id) {
        evaluacionRepository.deleteById(id);
    }
}
