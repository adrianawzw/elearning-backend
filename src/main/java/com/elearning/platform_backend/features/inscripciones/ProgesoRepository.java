package com.elearning.platform_backend.features.inscripciones;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProgesoRepository extends JpaRepository<Progreso, Long>{
    
    List<Progreso> findByCompletado(Boolean completado);
}
