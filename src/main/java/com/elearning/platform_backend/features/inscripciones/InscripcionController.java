package com.elearning.platform_backend.features.inscripciones;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @GetMapping
    public ResponseEntity<List<Inscripcion>> getAll() {
        return ResponseEntity.ok(
                inscripcionService.listar());
    }

    @GetMapping("/estudiante/{id}")
    public List<Inscripcion> getByEstudianteId(
            @PathVariable Long id) {

        return inscripcionService.buscarPorEstudiante(id);
    }

    @GetMapping("/curso/{id}")
    public List<Inscripcion> getByCursoId(
            @PathVariable Long id) {

        return inscripcionService.buscarPorCurso(id);
    }

    @PostMapping
    public ResponseEntity<Inscripcion> create(
            @RequestBody Inscripcion inscripcion) {

        return new ResponseEntity<>(
                inscripcionService.guardar(inscripcion),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        inscripcionService.eliminar(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}