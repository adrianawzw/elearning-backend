package com.elearning.platform_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elearning.platform_backend.entities.Pregunta;
import com.elearning.platform_backend.repositories.PreguntaRepository;

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

    public void eliminar(Long id){
        preguntaRepository.deleteById(id);
    }
}
