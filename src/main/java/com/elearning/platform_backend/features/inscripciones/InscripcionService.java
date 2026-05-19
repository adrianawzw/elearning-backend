package com.elearning.platform_backend.features.inscripciones;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;

    public List<Inscripcion> listar() {
        return inscripcionRepository.findAll();
    }

    public Inscripcion guardar(Inscripcion i) {
        return inscripcionRepository.save(i);
    }

    public List<Inscripcion> buscarPorEstado(String estado) {
        return inscripcionRepository.findByEstado(estado);
    }

    public void eliminar(Long id) {
        inscripcionRepository.deleteById(id);
    }

    // Operaciones de lectura en tablas Relacionadas (3)
    public List<Inscripcion> buscarPorEstudiante(Long estudianteId) {

        List<Inscripcion> inscripciones = inscripcionRepository.findByEstudianteId(estudianteId);

        if (inscripciones.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT,
                    "El estudiante no tiene inscripciones");
        }

        return inscripciones;
    }

    // Operaciones de lectura en tablas Relacionadas (6)
    public List<Inscripcion> buscarPorCurso(Long cursoId) {

       List<Inscripcion> inscripciones = inscripcionRepository.findByCursoId(cursoId);

        if (inscripciones.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT,
                    "El curso no tiene inscripciones");
        }

        return inscripciones;
    }
}
