package com.elearning.platform_backend.features.evaluaciones;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<EvaluacionReaderDTO>> getAll() {
        return ResponseEntity.ok(evaluacionService.listar());
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<EvaluacionReaderDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluacionService.buscarPorId(id));
    }

    @GetMapping("/curso/{cursoId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<EvaluacionReaderDTO>> getByCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(evaluacionService.buscarPorCurso(cursoId));
    }

    @GetMapping("/tipo/{tipo}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<EvaluacionReaderDTO>> getByTipo(@PathVariable TipoEvaluacion tipo) {
        return ResponseEntity.ok(evaluacionService.buscarPorTipo(tipo));
    }

    @PostMapping
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<EvaluacionReaderDTO> create(@Valid @RequestBody EvaluacionWriterDTO dto) {
        return new ResponseEntity<>(evaluacionService.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<EvaluacionReaderDTO> update(@PathVariable Long id,
            @Valid @RequestBody EvaluacionUpdateDTO dto) {
        return ResponseEntity.ok(evaluacionService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        evaluacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
