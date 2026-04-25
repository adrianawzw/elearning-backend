package com.elearning.platform_backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.platform_backend.entities.Usuario;
import com.elearning.platform_backend.services.UsuarioService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(
                usuarioService.listUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                usuarioService.getByID(id)

        );
    }

    @PostMapping
    public ResponseEntity<Usuario> create(
            @RequestBody Usuario usuario) {

        return new ResponseEntity<>(
                usuarioService.createUser(usuario),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        usuarioService.deleteUser(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(
            @PathVariable Long id,
            @RequestBody Usuario usuario) {

        return ResponseEntity.ok(
                usuarioService.updateUser(id, usuario));
    }
}
