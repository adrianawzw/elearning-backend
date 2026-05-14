package com.elearning.platform_backend.features.cursos;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> getAll() {
        return ResponseEntity.ok(
                cursoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                cursoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Curso> create(
            @RequestBody Curso curso) {

        return new ResponseEntity<>(
                cursoService.guardar(curso),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(
            @PathVariable Long id,
            @RequestBody Curso curso) {

        return ResponseEntity.ok(
                cursoService.actualizar(id, curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        cursoService.eliminar(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}