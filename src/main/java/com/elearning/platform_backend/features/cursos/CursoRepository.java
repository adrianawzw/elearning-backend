package com.elearning.platform_backend.features.cursos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long>{
    
    Optional<Curso> findByTitulo(String titulo);
    List<Curso> findByTituloContainingIgnoreCase(String titulo);

}
