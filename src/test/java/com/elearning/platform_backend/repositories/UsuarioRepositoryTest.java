package com.elearning.platform_backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.elearning.platform_backend.features.usuarios.Rol;
import com.elearning.platform_backend.features.usuarios.Usuario;
import com.elearning.platform_backend.features.usuarios.UsuarioRepository;

@DataJpaTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario crearUsuario(String email) {
        return Usuario.builder()
                .email(email)
                .password("12345678")
                .rol(Rol.ESTUDIANTE)
                .fechaRegistro(LocalDateTime.now())
                .build();
    }

    //POST
    @Test
    void shouldCreateUser() {
        Usuario usuario = crearUsuario("test@email.com");

        Usuario saved = usuarioRepository.save(usuario);

        assertNotNull(saved.getId());
        assertEquals("test@email.com", saved.getEmail());
    }

    //GET
    @Test
    void testFindByEmail() {
        Usuario usuario = crearUsuario("find@email.com");
        usuarioRepository.save(usuario);

        Optional<Usuario> result = usuarioRepository.findByEmail("find@email.com");

        assertTrue(result.isPresent());
        assertEquals(Rol.ESTUDIANTE, result.get().getRol());
    }

    //GET
    @Test
    void testFindByNombre() {
        usuarioRepository.save(crearUsuario("user1@email.com"));
        usuarioRepository.save(crearUsuario("user2@email.com"));

        List<Usuario> usuarios = usuarioRepository.findByRol(Rol.ESTUDIANTE);

        // Verifica que encuentre los usuarios creados con ese rol
        assertFalse(usuarios.isEmpty());
        assertTrue(usuarios.stream().allMatch(u -> u.getRol() == Rol.ESTUDIANTE));
    }

    //GET
    @Test
    void testFindByRol() {
        usuarioRepository.save(crearUsuario("user1@email.com"));
        usuarioRepository.save(crearUsuario("user2@email.com"));

        List<Usuario> usuarios = usuarioRepository.findByRol(Rol.ESTUDIANTE);

        // Verifica que encuentre los usuarios creados con ese rol
        assertFalse(usuarios.isEmpty());
        assertTrue(usuarios.stream().allMatch(u -> u.getRol() == Rol.ESTUDIANTE));
    }

    //PUT
    @Test
    void shouldUpdateUser() {
        Usuario usuario = usuarioRepository.save(crearUsuario("update@email.com"));

        usuario.setEmail("newemail@email.com");
        Usuario updated = usuarioRepository.save(usuario);

        assertEquals("newemail@email.com", updated.getEmail());
    }

    //DELETE
    @Test
    void shouldDeleteUser() {
        Usuario usuario = usuarioRepository.save(crearUsuario("delete@email.com"));
        Long id = usuario.getId();

        usuarioRepository.deleteById(id);

        Optional<Usuario> result = usuarioRepository.findById(id);
        assertFalse(result.isPresent());
    }
}
