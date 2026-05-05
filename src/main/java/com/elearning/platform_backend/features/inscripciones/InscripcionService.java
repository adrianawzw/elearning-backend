package com.elearning.platform_backend.features.inscripciones;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InscripcionService {
    
    private final InscripcionRepository inscripcionRepository;

    public List<Inscripcion> listar(){
        return inscripcionRepository.findAll();
    }

    public Inscripcion guardar(Inscripcion i){
        return inscripcionRepository.save(i);
    }

    public List<Inscripcion> buscarPorEstado(String estado){
        return inscripcionRepository.findByEstado(estado);
    }

    public void eliminar(Long id){
        inscripcionRepository.deleteById(id);
    }
}
