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

import com.elearning.platform_backend.entities.Rol;
import com.elearning.platform_backend.entities.Usuario;

@DataJpaTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario crearUsuario() {
        Usuario u = new Usuario();
        u.setNombre("Juan");
        u.setEmail("juan@email.com");
        u.setPassword("123456");
        u.setRol(Rol.ESTUDIANTE);
        u.setFechaRegistro(LocalDateTime.now());
        return u;
    }

    private Usuario crearUsuario2() {
        Usuario u = new Usuario();
        u.setNombre("Juan");
        u.setEmail("juan2@email.com");
        u.setPassword("123456");
        u.setRol(Rol.ESTUDIANTE);
        u.setFechaRegistro(LocalDateTime.now());
        return u;
    }

    //POST
    @Test
    void shouldCreateUser() {
        Usuario usuario = crearUsuario();

        Usuario saved = usuarioRepository.save(usuario);

        assertNotNull(saved.getId());
    }

    //GET
    @Test
    void testFindByEmail() {
        Usuario usuario = crearUsuario();
        usuarioRepository.save(usuario);

        Optional<Usuario> result = usuarioRepository.findByEmail(usuario.getEmail());

        assertTrue(result.isPresent());
    }

    //GET
    @Test
    void testFindByNombre() {
        usuarioRepository.save(crearUsuario());
        usuarioRepository.save(crearUsuario2());

        List<Usuario> usuarios = usuarioRepository.findByNombre("Juan");

        assertEquals(2, usuarios.size());
    }

    //GET
    @Test
    void testFindByRol() {
        usuarioRepository.save(crearUsuario());

        List<Usuario> usuarios = usuarioRepository.findByRol(Rol.ESTUDIANTE);

        assertEquals(1, usuarios.size());
    }

    //PUT
    @Test
    void shouldUpdateUser() {
        Usuario usuario = usuarioRepository.save(crearUsuario());

        usuario.setNombre("Pedro");

        Usuario updated = usuarioRepository.save(usuario);

        assertEquals("Pedro", updated.getNombre());
    }

    //DELETE
    @Test
    void shouldDeleteUser() {
        Usuario usuario = usuarioRepository.save(crearUsuario());

        usuarioRepository.deleteById(usuario.getId());

        Optional<Usuario> result = usuarioRepository.findById(usuario.getId());

        assertFalse(result.isPresent());
    }
}
