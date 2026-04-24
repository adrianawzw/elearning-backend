package com.elearning.platform_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.platform_backend.entities.Pregunta;
import java.util.List;


public interface PreguntaRepository extends JpaRepository<Pregunta, Long>{
    
    List<Pregunta> findByValorPunto(Integer valorPunto);
}
