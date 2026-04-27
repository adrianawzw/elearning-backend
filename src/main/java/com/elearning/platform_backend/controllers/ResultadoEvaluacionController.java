package com.elearning.platform_backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.elearning.platform_backend.entities.ResultadoEvaluacion;
import com.elearning.platform_backend.services.ResultadoEvaluacionService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/resultados-evaluacion")
@RequiredArgsConstructor
public class ResultadoEvaluacionController {

    private final ResultadoEvaluacionService resultadoEvaluacionService;

    @PostMapping("/respuesta")
    public ResponseEntity<ResultadoEvaluacion> guardarRespuesta(
            @RequestBody GuardarRespuestaRequest request) {

        return new ResponseEntity<>(
                resultadoEvaluacionService.guardarRespuesta(
                        request.getInscripcionId(),
                        request.getEvaluacionId(),
                        request.getRespuesta()),
                HttpStatus.CREATED);
    }

    @PutMapping("/{resultadoId}/calificar")
    public ResponseEntity<ResultadoEvaluacion> calificar(
            @PathVariable Long resultadoId,
            @RequestBody CalificarRequest request) {

        return ResponseEntity.ok(
                resultadoEvaluacionService.calificar(
                        resultadoId,
                        request.getNota()));
    }

    @GetMapping("/promedio/{inscripcionId}")
    public ResponseEntity<Double> calcularPromedio(
            @PathVariable Long inscripcionId) {

        return ResponseEntity.ok(
                resultadoEvaluacionService.calcularPromedio(inscripcionId));
    }

    @Data
    public static class GuardarRespuestaRequest {
        private Long inscripcionId;
        private Long evaluacionId;
        private String respuesta;
    }

    @Data
    public static class CalificarRequest {
        private Double nota;
    }
}