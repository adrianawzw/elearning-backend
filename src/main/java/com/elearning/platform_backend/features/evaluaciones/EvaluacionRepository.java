package com.elearning.platform_backend.features.evaluaciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

    List<Evaluacion> findByCursoId(Long cursoId);

    List<Evaluacion> findByTipo(TipoEvaluacion tipo);
}
