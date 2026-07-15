package com.elearning.platform_backend.features.usuarios;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/me")
    public ResponseEntity<UsuarioReaderDTO> getMe(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(usuarioService.findByEmail(userDetails.getUsername()));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioReaderDTO>> getAll() {
        return ResponseEntity.ok(
                usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioReaderDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                usuarioService.findById(id)

        );
    }

    @PostMapping
    public ResponseEntity<UsuarioReaderDTO> insertUser(
            @RequestBody UsuarioWriterDTO usuario) {

        return new ResponseEntity<>(
                usuarioService.create(usuario),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id) throws Exception{

        usuarioService.deleteById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/{id}/foto")
    public ResponseEntity<UsuarioReaderDTO> uploadFoto(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(usuarioService.uploadFoto(id, file));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioReaderDTO> update(
            @PathVariable Long id,
            @RequestBody UsuarioUpdateDTO usuario) throws Exception{

        return ResponseEntity.ok(
                usuarioService.update(id, usuario));
    }
}
