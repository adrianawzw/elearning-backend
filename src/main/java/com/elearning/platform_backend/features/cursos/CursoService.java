package com.elearning.platform_backend.features.cursos;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.elearning.platform_backend.features.usuarios.docentes.Docente;
import com.elearning.platform_backend.features.usuarios.docentes.DocenteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoService {
    private final CursoRepository cursoRepository;
    private final DocenteRepository docenteRepository;

    // Método Post para crear un nuevo Curso usando Transacional para verificar que
    // el Docente exista
    @Transactional
    public CursoReaderDTO create(CursoWriterDTO dto) {
        System.out.println("ID del docente recibido: " + dto.docenteID());
        
        if (dto.docenteID() == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID del docente no puede ser nulo");
    }

        Docente docente = docenteRepository.findById(dto.docenteID())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Docente no encontrado"));

        Curso curso = CursoMapper.toEntity(dto, docente);
        curso.setFechaCreacion(LocalDateTime.now());
        Curso cursoGuardado = cursoRepository.save(curso);

        return CursoMapper.toDto(cursoGuardado);
    }

    // Método GET para obtener el Curso por su ID
    public CursoReaderDTO findById(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));

        return CursoMapper.toDto(curso);
    }

    public List<CursoReaderDTO> findAllPublicos() {
        return cursoRepository.findAll().stream()
                .map(CursoMapper::toDto)
                .toList();
    }

    // Método GET para obtener a todos los Cursos
    public List<CursoReaderDTO> findAll() {
        if (cursoRepository.count() == 0)
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay cursos registrados");

        return cursoRepository.findAll().stream().map(curso -> findById(curso.getId())).toList();
    }

    // Método DELETE para eliminar un Curso por ID
    public void deleteById(Long id) {
        if (!cursoRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no existe");
        cursoRepository.deleteById(id);
    }

    // Método GET para obtener Curso por Nombre
    public CursoReaderDTO findByTitulo(String titulo) {
        Curso curso = cursoRepository.findByTitulo(titulo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));

        return CursoMapper.toDto(curso);
    }

    // Método GET para filtrado de Curso por contenido
    public List<CursoReaderDTO> findByTituloContaining(String titulo) {
        List<Curso> cursos = cursoRepository.findByTituloContainingIgnoreCase(titulo);

        if (cursos.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontraron resultados");

        return cursos.stream().map(CursoMapper::toDto).toList();
    }

    //Operaciones de lectura en tablas Relacionadas (5)
    public List<CursoReaderDTO> buscarPorDocente(Long docenteId) {

        List<Curso> cursos = cursoRepository.findByDocenteId(docenteId);

        if (cursos.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT,
                    "El docente no tiene cursos");
        }

        return cursos.stream()
                .map(CursoMapper::toDto)
                .toList();
    }

    // Método POST para actualizar los datos del Cursos
    @Transactional
    public CursoReaderDTO update(Long id, CursoUpdateDTO dto) throws Exception {

        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no existe"));

        if (dto.docenteID() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID del docente no puede ser nulo en la actualización");
        }

        Docente docente = docenteRepository.findById(dto.docenteID())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Docente no existe"));

        curso.setTitulo(dto.titulo());
        curso.setDescripcion(dto.descripcion());
        curso.setNivel(dto.nivel());
        curso.setDuracion(dto.duracion());
        curso.setCategoria(dto.categoria());
        curso.setImagenUrl(dto.imagenUrl());
        curso.setDocente(docente);

        return CursoMapper.toDto(cursoRepository.save(curso));
    }
}
