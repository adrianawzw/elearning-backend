package com.elearning.platform_backend.features.inscripciones;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgresoService {
    
    private final ProgesoRepository progresoRepository;

    public List<Progreso> listar(){
        return progresoRepository.findAll();
    }

    public Progreso guardar(Progreso progreso){
        return progresoRepository.save(progreso);
    }

    public List<Progreso> buscarCompletados(Boolean completado){
        return progresoRepository.findByCompletado(completado);
    }

    public void eliminar(Long id){
        progresoRepository.deleteById(id);
    }
}
