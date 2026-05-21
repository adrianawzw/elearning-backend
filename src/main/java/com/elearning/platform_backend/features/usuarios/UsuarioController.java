package com.elearning.platform_backend.features.usuarios;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<List<UsuarioReaderDTO>> getAll() {
        return ResponseEntity.ok(
                usuarioService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UsuarioReaderDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                usuarioService.findById(id)

        );
    }

    @PostMapping
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<UsuarioReaderDTO> insertUser(
            @RequestBody UsuarioWriterDTO usuario) {

        return new ResponseEntity<>(
                usuarioService.create(usuario),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id) throws Exception{

        usuarioService.deleteById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UsuarioReaderDTO> update(
            @PathVariable Long id,
            @RequestBody UsuarioUpdateDTO usuario) throws Exception{

        return ResponseEntity.ok(
                usuarioService.update(id, usuario));
    }
}
