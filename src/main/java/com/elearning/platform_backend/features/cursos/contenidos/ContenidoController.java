package com.elearning.platform_backend.features.cursos.contenidos;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/contenidos")
@RequiredArgsConstructor
public class ContenidoController {

    private final ContenidoService contenidoService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ContenidoReaderDTO>> getAll() {
        return ResponseEntity.ok(contenidoService.listarTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ContenidoReaderDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(contenidoService.buscarPorId(id));
    }

    @GetMapping("/tipo/{tipo}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ContenidoReaderDTO>> getByTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(contenidoService.buscarPorTipo(tipo));
    }

    @GetMapping("/curso/{cursoId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ContenidoReaderDTO>> getByCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(contenidoService.buscarPorCurso(cursoId));
    }

    @PostMapping
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<ContenidoReaderDTO> create(@Valid @RequestBody ContenidoWriterDTO dto) {
        return new ResponseEntity<>(contenidoService.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<ContenidoReaderDTO> update(@PathVariable Long id,
            @Valid @RequestBody ContenidoUpdateDTO dto) {
        return ResponseEntity.ok(contenidoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contenidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
