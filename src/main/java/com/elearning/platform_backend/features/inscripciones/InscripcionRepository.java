package com.elearning.platform_backend.features.inscripciones;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long>{

    List<Inscripcion> findByEstado(String estado);
    List<Inscripcion> findByEstudianteId(Long estudianteId);
    List<Inscripcion> findByCursoId(Long cursoId);
}
