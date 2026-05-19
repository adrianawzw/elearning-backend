package com.elearning.platform_backend.features.evaluaciones;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long>{
    
    List<Evaluacion> findByCursoId(Long cursoId);
    
}
