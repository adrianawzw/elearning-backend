package com.elearning.platform_backend.features.evaluaciones;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.platform_backend.features.cursos.Curso;

import java.util.List;


public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long>{
    
    List<Evaluacion> findByCurso(Curso curso);
    
}
