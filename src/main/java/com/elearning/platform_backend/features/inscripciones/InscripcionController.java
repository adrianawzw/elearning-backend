package com.elearning.platform_backend.features.inscripciones;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @GetMapping
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<List<InscripcionReaderDTO>> getAll() {
        return ResponseEntity.ok(inscripcionService.listar());
    }

    @GetMapping("/estudiante/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<InscripcionReaderDTO>> getByEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.buscarPorEstudiante(id));
    }

    @GetMapping("/curso/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<InscripcionReaderDTO>> getByCurso(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.buscarPorCurso(id));
    }

    @GetMapping("/estado/{estado}")
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<List<InscripcionReaderDTO>> getByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(inscripcionService.buscarPorEstado(estado));
    }

    @PostMapping
    @PreAuthorize("hasRole('ESTUDIANTE')")
    public ResponseEntity<InscripcionReaderDTO> create(@Valid @RequestBody InscripcionWriterDTO dto) {
        return new ResponseEntity<>(inscripcionService.guardar(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/estado")
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<InscripcionReaderDTO> updateEstado(@PathVariable Long id,
            @RequestParam String estado) {
        return ResponseEntity.ok(inscripcionService.actualizarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inscripcionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
