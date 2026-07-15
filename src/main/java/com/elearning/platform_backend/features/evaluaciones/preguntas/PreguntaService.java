package com.elearning.platform_backend.features.evaluaciones.preguntas;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PreguntaService {
    
    private final PreguntaRepository preguntaRepository;

    public List<Pregunta> listar(){
        return preguntaRepository.findAll();
    }

    public Pregunta guardar(Pregunta pregunta){
        return preguntaRepository.save(pregunta);
    }

    public List<Pregunta> buscarPorValor(Integer puntos){
        return preguntaRepository.findByValorPunto(puntos);
    }

    public List<Pregunta> buscarPorEvaluacion(Long evaluacionId){
        return preguntaRepository.findByEvaluacionId(evaluacionId);
    }

    public void eliminar(Long id){
        preguntaRepository.deleteById(id);
    }
}
