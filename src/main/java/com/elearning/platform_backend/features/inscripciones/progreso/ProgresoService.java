package com.elearning.platform_backend.features.inscripciones.progreso;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.elearning.platform_backend.features.cursos.contenidos.ContenidoRepository;
import com.elearning.platform_backend.features.inscripciones.Inscripcion;
import com.elearning.platform_backend.features.inscripciones.InscripcionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgresoService {

    private final ProgesoRepository progresoRepository;
    private final InscripcionRepository inscripcionRepository;
    private final ContenidoRepository contenidoRepository;

    public List<Progreso> listar() {
        return progresoRepository.findAll();
    }

    @Transactional
    public Progreso guardar(Progreso progreso) {
        if (progreso.getCompletado() == null) progreso.setCompletado(false);
        if (Boolean.TRUE.equals(progreso.getCompletado()) && progreso.getFechaCompletado() == null) {
            progreso.setFechaCompletado(LocalDateTime.now());
        }
        Progreso guardado = progresoRepository.save(progreso);

        // Si se marcó como completado, verificar si el curso está 100% completado
        if (Boolean.TRUE.equals(guardado.getCompletado()) && guardado.getInscripcion() != null) {
            verificarCursoCompletado(guardado.getInscripcion().getId());
        }

        return guardado;
    }

    public List<Progreso> buscarCompletados(Boolean completado) {
        return progresoRepository.findByCompletado(completado);
    }

    public List<Progreso> buscarPorInscripcion(Long inscripcionId) {
        return progresoRepository.findByInscripcionId(inscripcionId);
    }

    public List<Progreso> buscarCompletadosPorEstudiante(Long estudianteId) {
        return progresoRepository.findCompletadosByEstudianteId(estudianteId);
    }

    public void eliminar(Long id) {
        progresoRepository.deleteById(id);
    }

    private void verificarCursoCompletado(Long inscripcionId) {
        Inscripcion inscripcion = inscripcionRepository.findById(inscripcionId).orElse(null);
        if (inscripcion == null || inscripcion.getCurso() == null) return;

        long totalContenidos = contenidoRepository.findByCursoId(inscripcion.getCurso().getId()).size();
        long completados = progresoRepository.findByInscripcionId(inscripcionId)
                .stream().filter(p -> Boolean.TRUE.equals(p.getCompletado())).count();

        if (totalContenidos > 0 && completados >= totalContenidos) {
            inscripcion.setEstado("FINALIZADO");
            inscripcionRepository.save(inscripcion);
        }
    }
}
