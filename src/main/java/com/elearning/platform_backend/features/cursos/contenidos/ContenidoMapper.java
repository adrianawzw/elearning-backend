package com.elearning.platform_backend.features.cursos.contenidos;

import com.elearning.platform_backend.features.cursos.Curso;

public class ContenidoMapper {

    public static Contenido toEntity(ContenidoWriterDTO dto, Curso curso) {
        Contenido contenido = new Contenido();
        contenido.setTitulo(dto.titulo());
        contenido.setUrlMaterial(dto.urlMaterial());
        contenido.setTipo(dto.tipo());
        contenido.setDuracion(dto.duracion());
        contenido.setCurso(curso);
        return contenido;
    }

    public static ContenidoReaderDTO toDto(Contenido contenido) {
        return new ContenidoReaderDTO(
            contenido.getId(),
            contenido.getTitulo(),
            contenido.getUrlMaterial(),
            contenido.getTipo(),
            contenido.getDuracion(),
            contenido.getCurso() != null ? contenido.getCurso().getId() : null
        );
    }
}
