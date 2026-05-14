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

import com.elearning.platform_backend.features.usuarios.Rol;
import com.elearning.platform_backend.features.usuarios.UsuarioController;
import com.elearning.platform_backend.features.usuarios.UsuarioReaderDTO;
import com.elearning.platform_backend.features.usuarios.UsuarioService;
import com.elearning.platform_backend.features.usuarios.UsuarioUpdateDTO;
import com.elearning.platform_backend.features.usuarios.UsuarioWriterDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UsuarioService usuarioService;

    private UsuarioReaderDTO crearUsuarioReaderDTO() {
        return new UsuarioReaderDTO(
                1L,
                "juan@email.com",
                "Juan",
                "Perez",
                Rol.ESTUDIANTE,
                LocalDateTime.now(),
                null,
                "EST123"
        );
    }

    private UsuarioWriterDTO crearUsuarioWriterDTO() {
        return new UsuarioWriterDTO(
                "juan@email.com",
                "12345678", // Min 8 caracteres según validación
                "Juan",
                "Perez",
                Rol.ESTUDIANTE,
                null,
                "EST123"
        );
    }

    @Test
    void testGetAll() throws Exception {
        /*List<Usuario> usuarios = List.of(crearUsuario());

        when(usuarioService.findAll()).thenReturn(usuarios);

        mockMvc.perform(get("/api/v1/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(usuarios)));

        verify(usuarioService).findAll();*/
        List<UsuarioReaderDTO> usuarios = List.of(crearUsuarioReaderDTO());

        when(usuarioService.findAll()).thenReturn(usuarios);

        mockMvc.perform(get("/api/v1/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(usuarios)));

        verify(usuarioService).findAll();
    }

    @Test
    void testGetById() throws Exception {
        /*Usuario usuario = crearUsuario();

        when(usuarioService.findById(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/v1/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(usuario)));

        verify(usuarioService).findById(1L);*/
        UsuarioReaderDTO dto = crearUsuarioReaderDTO();

        when(usuarioService.findById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));

        verify(usuarioService).findById(1L);
    }

    @Test
    void testCreate() throws Exception {
        /*Usuario usuario = crearUsuario();

        when(usuarioService.create(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(usuario)));

        verify(usuarioService).create(any(Usuario.class));*/
        UsuarioWriterDTO writerDto = crearUsuarioWriterDTO();
        UsuarioReaderDTO readerDto = crearUsuarioReaderDTO();

        when(usuarioService.create(any(UsuarioWriterDTO.class))).thenReturn(readerDto);

        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(writerDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(readerDto)));

        verify(usuarioService).create(any(UsuarioWriterDTO.class));
    }

    @Test
    void testUpdate() throws Exception {
        /*Usuario usuarioActualizado = crearUsuario();
        usuarioActualizado.setNombre("Pedro");

        when(usuarioService.update(eq(1L), any(Usuario.class))).thenReturn(usuarioActualizado);

        mockMvc.perform(put("/api/v1/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioActualizado)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(usuarioActualizado)));

        verify(usuarioService).update(eq(1L), any(Usuario.class));*/
        UsuarioUpdateDTO updateDto = new UsuarioUpdateDTO("Pedro", "Gomez", null, "EST123");
        UsuarioReaderDTO readerDto = crearUsuarioReaderDTO(); // Simula el retorno actualizado

        when(usuarioService.update(eq(1L), any(UsuarioUpdateDTO.class))).thenReturn(readerDto);

        mockMvc.perform(put("/api/v1/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(readerDto)));

        verify(usuarioService).update(eq(1L), any(UsuarioUpdateDTO.class));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(usuarioService).deleteById(1L);

        mockMvc.perform(delete("/api/v1/usuarios/1"))
                .andExpect(status().isNoContent());

        verify(usuarioService).deleteById(1L);
    }
}