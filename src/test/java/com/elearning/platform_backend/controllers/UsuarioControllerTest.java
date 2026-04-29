package com.elearning.platform_backend.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.elearning.platform_backend.entities.Rol;
import com.elearning.platform_backend.entities.Usuario;
import com.elearning.platform_backend.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UsuarioService usuarioService;

    private Usuario crearUsuario() {
        Usuario u = new Usuario();
        u.setNombre("Juan");
        u.setEmail("juan@email.com");
        u.setPassword("123456");
        u.setRol(Rol.ESTUDIANTE);
        u.setFechaRegistro(LocalDateTime.now());
        return u;
    }

    @Test
    void testGetAll() throws Exception {
        List<Usuario> usuarios = List.of(crearUsuario());

        when(usuarioService.listUsers()).thenReturn(usuarios);

        mockMvc.perform(get("/api/v1/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(usuarios)));

        verify(usuarioService).listUsers();
    }

    @Test
    void testGetById() throws Exception {
        Usuario usuario = crearUsuario();

        when(usuarioService.getByID(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/v1/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(usuario)));

        verify(usuarioService).getByID(1L);
    }

    @Test
    void testCreate() throws Exception {
        Usuario usuario = crearUsuario();

        when(usuarioService.createUser(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(usuario)));

        verify(usuarioService).createUser(any(Usuario.class));
    }

    @Test
    void testUpdate() throws Exception {
        Usuario usuarioActualizado = crearUsuario();
        usuarioActualizado.setNombre("Pedro");

        when(usuarioService.updateUser(eq(1L), any(Usuario.class))).thenReturn(usuarioActualizado);

        mockMvc.perform(put("/api/v1/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioActualizado)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(usuarioActualizado)));

        verify(usuarioService).updateUser(eq(1L), any(Usuario.class));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(usuarioService).deleteUser(1L);

        mockMvc.perform(delete("/api/v1/usuarios/1"))
                .andExpect(status().isNoContent());

        verify(usuarioService).deleteUser(1L);
    }
}