package com.elearning.platform_backend.features.inscripciones;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.elearning.platform_backend.features.cursos.Curso;
import com.elearning.platform_backend.features.cursos.CursoRepository;
import com.elearning.platform_backend.features.usuarios.estudiantes.Estudiante;
import com.elearning.platform_backend.features.usuarios.estudiantes.EstudianteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    public List<InscripcionReaderDTO> listar() {
        return inscripcionRepository.findAll().stream()
                .map(InscripcionMapper::toDto)
                .toList();
    }

    // Operaciones de lectura en tablas Relacionadas (3)
    public List<InscripcionReaderDTO> buscarPorEstudiante(Long estudianteId) {
        return inscripcionRepository.findByEstudianteId(estudianteId).stream()
                .map(InscripcionMapper::toDto).toList();
    }

    // Operaciones de lectura en tablas Relacionadas (6)
    public List<InscripcionReaderDTO> buscarPorCurso(Long cursoId) {
        List<Inscripcion> inscripciones = inscripcionRepository.findByCursoId(cursoId);
        if (inscripciones.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "El curso no tiene inscripciones");
        return inscripciones.stream().map(InscripcionMapper::toDto).toList();
    }

    public List<InscripcionReaderDTO> buscarFinalizadosPorCurso(Long cursoId) {
        return inscripcionRepository.findByCursoId(cursoId).stream()
                .filter(i -> "FINALIZADO".equals(i.getEstado()))
                .map(InscripcionMapper::toDto)
                .toList();
    }

    public List<InscripcionReaderDTO> buscarPorEstado(String estado) {
        List<Inscripcion> inscripciones = inscripcionRepository.findByEstado(estado);
        if (inscripciones.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay inscripciones con ese estado");
        return inscripciones.stream().map(InscripcionMapper::toDto).toList();
    }

    // Operaciones de escritura en tablas Relacionadas (1)
    @Transactional
    public InscripcionReaderDTO guardar(InscripcionWriterDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(dto.estudianteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no encontrado"));
        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudiante(estudiante);
        inscripcion.setCurso(curso);
        inscripcion.setEstado("ACTIVO");
        inscripcion.setFechaInscripcion(LocalDateTime.now());

        return InscripcionMapper.toDto(inscripcionRepository.save(inscripcion));
    }

    // Operaciones de actualización en tablas Relacionadas (1)
    @Transactional
    public InscripcionReaderDTO actualizarEstado(Long id, String estado) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inscripcion no encontrada"));
        inscripcion.setEstado(estado);
        return InscripcionMapper.toDto(inscripcionRepository.save(inscripcion));
    }

    public void eliminar(Long id) {
        if (!inscripcionRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inscripcion no encontrada");
        inscripcionRepository.deleteById(id);
    }
}
