package com.elearning.platform_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.platform_backend.entities.Curso;
import com.elearning.platform_backend.entities.Evaluacion;
import java.util.List;


public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long>{
    
    List<Evaluacion> findByCurso(Curso curso);
    
}
