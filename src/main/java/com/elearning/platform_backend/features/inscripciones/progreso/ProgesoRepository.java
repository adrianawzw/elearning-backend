package com.elearning.platform_backend.features.inscripciones.progreso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProgesoRepository extends JpaRepository<Progreso, Long>{
    
    List<Progreso> findByCompletado(Boolean completado);
    List<Progreso> findByInscripcionId(Long inscripcionId);

    @Query("SELECT p FROM Progreso p WHERE p.inscripcion.estudiante.id = :estudianteId AND p.completado = true")
    List<Progreso> findCompletadosByEstudianteId(@Param("estudianteId") Long estudianteId);
}
