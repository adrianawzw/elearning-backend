package com.elearning.platform_backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.elearning.platform_backend.entities.Progreso;
import com.elearning.platform_backend.services.ProgresoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/progresos")
@RequiredArgsConstructor
public class ProgresoController {

    private final ProgresoService progresoService;

    @GetMapping
    public ResponseEntity<List<Progreso>> getAll() {
        return ResponseEntity.ok(
                progresoService.listar());
    }

    @GetMapping("/completado/{completado}")
    public ResponseEntity<List<Progreso>> getByCompletado(
            @PathVariable Boolean completado) {

        return ResponseEntity.ok(
                progresoService.buscarCompletados(completado));
    }

    @PostMapping
    public ResponseEntity<Progreso> create(
            @RequestBody Progreso progreso) {

        return new ResponseEntity<>(
                progresoService.guardar(progreso),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        progresoService.eliminar(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}