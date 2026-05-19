package com.elearning.platform_backend.features.evaluaciones.preguntas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PreguntaRepository extends JpaRepository<Pregunta, Long>{
    
    List<Pregunta> findByValorPunto(Integer valorPunto);
}
