package com.elearning.platform_backend.features.evaluaciones;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.elearning.platform_backend.features.inscripciones.Inscripcion;
import com.elearning.platform_backend.features.inscripciones.InscripcionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultadoEvaluacionService {

    private final ResultadoEvaluacionRepository resultadoRepository;
    private final InscripcionRepository inscripcionRepository;

    /*
     * lógica:
     * guardar respuesta
     * asignar nota
     * calcular promedio
     */

    public ResultadoEvaluacion guardarRespuesta(Long inscripcionId, Long evaluacionId, String respuesta) {

        ResultadoEvaluacion r = new ResultadoEvaluacion();
        r.setRespuestaTexto(respuesta);
        r.setFecha(LocalDateTime.now());

        return resultadoRepository.save(r);
    }

    public ResultadoEvaluacion calificar(Long resultadoId, Double nota) {

        ResultadoEvaluacion r = resultadoRepository.findById(resultadoId)
                .orElseThrow();

        r.setNota(nota);

        return resultadoRepository.save(r);
    }

    public Double calcularPromedio(Long inscripcionId) {

        List<ResultadoEvaluacion> resultados = resultadoRepository.findAll(); 

        double suma = resultados.stream()
                .filter(r -> r.getNota() != null)
                .mapToDouble(ResultadoEvaluacion::getNota)
                .sum();

        long cantidad = resultados.stream()
                .filter(r -> r.getNota() != null)
                .count();

        return cantidad == 0 ? 0 : suma / cantidad;
    }

    public void actualizarNotaFinal(Long inscripcionId) {

        Double promedio = calcularPromedio(inscripcionId);

        Inscripcion ins = inscripcionRepository.findById(inscripcionId)
                .orElseThrow();

        ins.setNotaFinal(promedio);

        inscripcionRepository.save(ins);
    }
}
