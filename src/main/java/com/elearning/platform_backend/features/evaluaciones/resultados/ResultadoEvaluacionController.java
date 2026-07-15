package com.elearning.platform_backend.features.evaluaciones.resultados;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/resultados-evaluacion")
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

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<ResultadoEvaluacion>> getByEstudiante(
            @PathVariable Long estudianteId) {

        return ResponseEntity.ok(
                resultadoEvaluacionService.buscarPorEstudiante(estudianteId));
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