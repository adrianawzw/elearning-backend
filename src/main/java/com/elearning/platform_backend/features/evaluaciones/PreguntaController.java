package com.elearning.platform_backend.features.evaluaciones;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/preguntas")
@RequiredArgsConstructor
public class PreguntaController {

    private final PreguntaService preguntaService;

    @GetMapping
    public ResponseEntity<List<Pregunta>> getAll() {
        return ResponseEntity.ok(
                preguntaService.listar());
    }

    @GetMapping("/valor/{puntos}")
    public ResponseEntity<List<Pregunta>> getByValor(
            @PathVariable Integer puntos) {

        return ResponseEntity.ok(
                preguntaService.buscarPorValor(puntos));
    }

    @PostMapping
    public ResponseEntity<Pregunta> create(
            @RequestBody Pregunta pregunta) {

        return new ResponseEntity<>(
                preguntaService.guardar(pregunta),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        preguntaService.eliminar(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}