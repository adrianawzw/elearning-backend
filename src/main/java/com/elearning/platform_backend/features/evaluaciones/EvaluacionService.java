package com.elearning.platform_backend.features.evaluaciones;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.elearning.platform_backend.features.cursos.Curso;
import com.elearning.platform_backend.features.cursos.CursoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;
    private final CursoRepository cursoRepository;

    public List<EvaluacionReaderDTO> listar() {
        return evaluacionRepository.findAll()
                .stream()
                .map(EvaluacionMapper::toDto)
                .toList();
    }

    public EvaluacionReaderDTO buscarPorId(Long id) {
        Evaluacion evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluacion no encontrada"));
        return EvaluacionMapper.toDto(evaluacion);
    }

    public List<EvaluacionReaderDTO> buscarPorCurso(Long cursoId) {
        return evaluacionRepository.findByCursoId(cursoId)
                .stream().map(EvaluacionMapper::toDto).toList();
    }

    public List<EvaluacionReaderDTO> buscarPorTipo(TipoEvaluacion tipo) {
        List<Evaluacion> evaluaciones = evaluacionRepository.findByTipo(tipo);
        if (evaluaciones.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay evaluaciones con ese tipo");
        return evaluaciones.stream().map(EvaluacionMapper::toDto).toList();
    }

    @Transactional
    public EvaluacionReaderDTO guardar(EvaluacionWriterDTO dto) {
        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));
        Evaluacion evaluacion = EvaluacionMapper.toEntity(dto, curso);
        return EvaluacionMapper.toDto(evaluacionRepository.save(evaluacion));
    }

    @Transactional
    public EvaluacionReaderDTO actualizar(Long id, EvaluacionUpdateDTO dto) {
        Evaluacion evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluacion no encontrada"));
        evaluacion.setTitulo(dto.titulo());
        evaluacion.setTipo(dto.tipo());
        evaluacion.setDescripcion(dto.descripcion());
        evaluacion.setPuntajeMinimo(dto.puntajeMinimo());
        return EvaluacionMapper.toDto(evaluacionRepository.save(evaluacion));
    }

    public void eliminar(Long id) {
        if (!evaluacionRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluacion no encontrada");
        evaluacionRepository.deleteById(id);
    }
}
