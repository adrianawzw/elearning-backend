package com.elearning.platform_backend.features.inscripciones.progreso;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgresoService {

    private final ProgesoRepository progresoRepository;

    public List<Progreso> listar() {
        return progresoRepository.findAll();
    }

    public Progreso guardar(Progreso progreso) {
        return progresoRepository.save(progreso);
    }

    public List<Progreso> buscarCompletados(Boolean completado) {
        return progresoRepository.findByCompletado(completado);
    }

    //Operaciones de lectura en tablas Relacionadas (4)
    public List<Progreso> buscarPorInscripcion(Long inscripcionId) {

        List<Progreso> progresos = progresoRepository.findByInscripcionId(inscripcionId);

        if (progresos.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT,
                    "No existen progresos para esta inscripción");
        }

        return progresos;
    }

    public void eliminar(Long id) {
        progresoRepository.deleteById(id);
    }
}
