package com.elearning.platform_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elearning.platform_backend.entities.Curso;
import com.elearning.platform_backend.entities.Evaluacion;
import com.elearning.platform_backend.repositories.EvaluacionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluacionService {
    private final EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> listar(){
        return evaluacionRepository.findAll();
    }

    public Evaluacion guardar(Evaluacion e){
        return evaluacionRepository.save(e);
    }

    public Evaluacion buscarPorId(Long id){
        return evaluacionRepository.findById(id)
                .orElseThrow();
    }

    public List<Evaluacion> buscarPorCurso(Curso curso){
        return evaluacionRepository.findByCurso(curso);
    }

    public void eliminar(Long id){
        evaluacionRepository.deleteById(id);
    }
}
