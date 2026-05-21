package com.elearning.platform_backend.features.cursos.contenidos;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.elearning.platform_backend.features.cursos.Curso;
import com.elearning.platform_backend.features.cursos.CursoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContenidoService {

    private final ContenidoRepository contenidoRepository;
    private final CursoRepository cursoRepository;

    public List<ContenidoReaderDTO> listarTodos() {
        return contenidoRepository.findAll()
                .stream()
                .map(ContenidoMapper::toDto)
                .toList();
    }

    public ContenidoReaderDTO buscarPorId(Long id) {
        Contenido contenido = contenidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contenido no encontrado"));
        return ContenidoMapper.toDto(contenido);
    }

    public List<ContenidoReaderDTO> buscarPorTipo(String tipo) {
        List<Contenido> contenidos = contenidoRepository.findAllByTipo(tipo);
        if (contenidos.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay contenidos con ese tipo");
        return contenidos.stream().map(ContenidoMapper::toDto).toList();
    }

    public List<ContenidoReaderDTO> buscarPorCurso(Long cursoId) {
        List<Contenido> contenidos = contenidoRepository.findByCursoId(cursoId);
        if (contenidos.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay contenidos para este curso");
        return contenidos.stream().map(ContenidoMapper::toDto).toList();
    }

    @Transactional
    public ContenidoReaderDTO guardar(ContenidoWriterDTO dto) {
        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));
        Contenido contenido = ContenidoMapper.toEntity(dto, curso);
        return ContenidoMapper.toDto(contenidoRepository.save(contenido));
    }

    @Transactional
    public ContenidoReaderDTO actualizar(Long id, ContenidoUpdateDTO dto) {
        Contenido contenido = contenidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contenido no encontrado"));
        contenido.setTitulo(dto.titulo());
        contenido.setUrlMaterial(dto.urlMaterial());
        contenido.setTipo(dto.tipo());
        return ContenidoMapper.toDto(contenidoRepository.save(contenido));
    }

    public void eliminar(Long id) {
        if (!contenidoRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contenido no encontrado");
        contenidoRepository.deleteById(id);
    }
}
