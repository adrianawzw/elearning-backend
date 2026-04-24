package com.elearning.platform_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.platform_backend.entities.Inscripcion;
import java.util.List;


public interface InscripcionRepository extends JpaRepository<Inscripcion, Long>{

    List<Inscripcion> findByEstado(String estado);
}
