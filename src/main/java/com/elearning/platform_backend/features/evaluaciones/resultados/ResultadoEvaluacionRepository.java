package com.elearning.platform_backend.features.evaluaciones.resultados;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResultadoEvaluacionRepository extends JpaRepository<ResultadoEvaluacion, Long>{

    @Query("SELECT r FROM ResultadoEvaluacion r WHERE r.inscripcion.estudiante.id = :estudianteId")
    List<ResultadoEvaluacion> findByEstudianteId(@Param("estudianteId") Long estudianteId);
}
