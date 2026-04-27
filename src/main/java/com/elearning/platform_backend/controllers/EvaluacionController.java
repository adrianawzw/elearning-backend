package com.elearning.platform_backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.elearning.platform_backend.entities.Evaluacion;
import com.elearning.platform_backend.services.EvaluacionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    @GetMapping
    public ResponseEntity<List<Evaluacion>> getAll() {
        return ResponseEntity.ok(
                evaluacionService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                evaluacionService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Evaluacion> create(
            @RequestBody Evaluacion evaluacion) {

        return new ResponseEntity<>(
                evaluacionService.guardar(evaluacion),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluacion> update(
            @PathVariable Long id,
            @RequestBody Evaluacion evaluacion) {

        Evaluacion evaluacionActual = evaluacionService.buscarPorId(id);
        evaluacionActual.setTitulo(evaluacion.getTitulo());
        evaluacionActual.setTipo(evaluacion.getTipo());
        evaluacionActual.setDescripcion(evaluacion.getDescripcion());
        evaluacionActual.setCurso(evaluacion.getCurso());
        evaluacionActual.setPuntajeMinimo(evaluacion.getPuntajeMinimo());

        return ResponseEntity.ok(
                evaluacionService.guardar(evaluacionActual));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        evaluacionService.eliminar(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}