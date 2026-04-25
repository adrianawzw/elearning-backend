package com.elearning.platform_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.platform_backend.entities.Contenido;


public interface ContenidoRepository extends JpaRepository<Contenido, Long>{

    Optional<Contenido> findByTipo(String tipo);

}
