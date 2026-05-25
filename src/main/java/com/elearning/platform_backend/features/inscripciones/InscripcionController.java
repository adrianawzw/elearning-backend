package com.elearning.platform_backend.features.inscripciones;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @GetMapping
    public ResponseEntity<List<InscripcionReaderDTO>> getAll() {
        return ResponseEntity.ok(inscripcionService.listar());
    }

    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<InscripcionReaderDTO>> getByEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.buscarPorEstudiante(id));
    }

    @GetMapping("/curso/{id}")
    public ResponseEntity<List<InscripcionReaderDTO>> getByCurso(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.buscarPorCurso(id));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<InscripcionReaderDTO>> getByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(inscripcionService.buscarPorEstado(estado));
    }

    @PostMapping
    public ResponseEntity<InscripcionReaderDTO> create(@Valid @RequestBody InscripcionWriterDTO dto) {
        return new ResponseEntity<>(inscripcionService.guardar(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<InscripcionReaderDTO> updateEstado(@PathVariable Long id,
            @RequestParam String estado) {
        return ResponseEntity.ok(inscripcionService.actualizarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inscripcionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
