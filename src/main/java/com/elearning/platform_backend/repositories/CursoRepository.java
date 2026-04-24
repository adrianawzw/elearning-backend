package com.elearning.platform_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.platform_backend.entities.Curso;
import java.util.List;


public interface CursoRepository extends JpaRepository<Curso, Long>{
    
    List<Curso> findByTitulo(String titulo);
    
}
