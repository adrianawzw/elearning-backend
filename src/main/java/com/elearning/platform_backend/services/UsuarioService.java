package com.elearning.platform_backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.elearning.platform_backend.entities.Usuario;
import com.elearning.platform_backend.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario createUser(Usuario usuario) {

        Optional<Usuario> thereIs = usuarioRepository.findByEmail(usuario.getEmail());

        if (thereIs.isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        usuario.setFechaRegistro(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

    public Usuario getByID(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public List<Usuario> listUsers() {
        return usuarioRepository.findAll();
    }

    public void deleteUser(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no existe");
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario updateUser(Long id, Usuario nuevo) {

        Usuario usuario = getByID(id);

        usuario.setNombre(nuevo.getNombre());
        usuario.setEmail(nuevo.getEmail());
        usuario.setRol(nuevo.getRol());

        return usuarioRepository.save(usuario);
    }
}
