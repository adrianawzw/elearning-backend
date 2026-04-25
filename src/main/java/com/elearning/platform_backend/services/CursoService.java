package com.elearning.platform_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elearning.platform_backend.entities.Curso;
import com.elearning.platform_backend.repositories.CursoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoService {
    private final CursoRepository cursoRepository;

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public List<Curso> buscarPorTitulo(String titulo){
        return cursoRepository.findByTitulo(titulo);
    }

    public Curso guardar(Curso curso){
        return cursoRepository.save(curso);
    }

    public Curso actualizar(Long id, Curso nuevo){
        Curso curso = buscarPorId(id);

        curso.setTitulo(nuevo.getTitulo());
        curso.setDescripcion(nuevo.getDescripcion());

        return cursoRepository.save(curso);
    }

    public void eliminar(Long id){
        cursoRepository.deleteById(id);
    }
}
