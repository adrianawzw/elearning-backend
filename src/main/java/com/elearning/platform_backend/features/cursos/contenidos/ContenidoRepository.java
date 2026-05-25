package com.elearning.platform_backend.features.cursos.contenidos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContenidoRepository extends JpaRepository<Contenido, Long> {

    List<Contenido> findAllByTipo(String tipo);

    List<Contenido> findByCursoId(Long cursoId);
}
