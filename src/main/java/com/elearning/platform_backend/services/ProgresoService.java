package com.elearning.platform_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elearning.platform_backend.entities.Progreso;
import com.elearning.platform_backend.repositories.ProgesoRepository;

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
