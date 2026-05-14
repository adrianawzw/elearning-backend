package com.elearning.platform_backend.features.cursos;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/contenidos")
@RequiredArgsConstructor
public class ContenidoController {

    private final ContenidoService contenidoService;

    @GetMapping
    public ResponseEntity<List<Contenido>> getAll() {
        return ResponseEntity.ok(
                contenidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contenido> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                contenidoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Contenido> create(
            @RequestBody Contenido contenido) {

        return new ResponseEntity<>(
                contenidoService.guardar(contenido),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contenido> update(
            @PathVariable Long id,
            @RequestBody Contenido contenido) {

        Contenido contenidoActual = contenidoService.buscarPorId(id);
        contenidoActual.setTitulo(contenido.getTitulo());
        contenidoActual.setUrlMaterial(contenido.getUrlMaterial());
        contenidoActual.setTipo(contenido.getTipo());
        contenidoActual.setCurso(contenido.getCurso());

        return ResponseEntity.ok(
                contenidoService.guardar(contenidoActual));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        contenidoService.eliminar(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}