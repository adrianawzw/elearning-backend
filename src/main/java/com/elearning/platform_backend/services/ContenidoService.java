package com.elearning.platform_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.elearning.platform_backend.entities.Contenido;
import com.elearning.platform_backend.repositories.ContenidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContenidoService {
    private final ContenidoRepository contenidoRepository;

    public List<Contenido> listarTodos(){
        return contenidoRepository.findAll();
    }

    public Contenido buscarPorId(Long id){
        return contenidoRepository.findById(id)
                .orElseThrow();
    }

    public Optional<Contenido> buscarPorTipo(String tipo){
        return contenidoRepository.findByTipo(tipo);
    }

    public Contenido guardar(Contenido contenido){
        return contenidoRepository.save(contenido);
    }

    public void eliminar(Long id){
        contenidoRepository.deleteById(id);
    }
}
