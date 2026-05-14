package com.elearning.platform_backend.features.evaluaciones;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elearning.platform_backend.features.cursos.Curso;

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
