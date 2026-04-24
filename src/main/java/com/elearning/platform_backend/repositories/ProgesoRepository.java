package com.elearning.platform_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.platform_backend.entities.Progreso;
import java.util.List;


public interface ProgesoRepository extends JpaRepository<Progreso, Long>{
    
    List<Progreso> findByCompletado(Boolean completado);
}
