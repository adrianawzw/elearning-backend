package com.elearning.platform_backend.features.cursos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ContenidoRepository extends JpaRepository<Contenido, Long>{

    Optional<Contenido> findByTipo(String tipo);

}
