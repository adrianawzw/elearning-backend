package com.elearning.platform_backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.elearning.platform_backend.entities.ResultadoEvaluacion;
import com.elearning.platform_backend.repositories.ResultadoEvaluacionRepository;

@ExtendWith(MockitoExtension.class)
public class ResultadoEvaluacionServiceTest {

    @Mock
    private ResultadoEvaluacionRepository repositorio;

    @InjectMocks
    private ResultadoEvaluacionService service;

    @Test
    void guardarRespuesta_debeGuardarCorrectamente() {
        ResultadoEvaluacion r = new ResultadoEvaluacion();
        r.setRespuestaTexto("Respuesta");

        when(repositorio.save(any())).thenReturn(r);

        ResultadoEvaluacion resultado = service.guardarRespuesta(1L, 1L, "Respuesta");

        assertEquals("Respuesta", resultado.getRespuestaTexto());
    }

    @Test
    void calcularPromedio_debeCalcularCorrectamente() {

        ResultadoEvaluacion r1 = new ResultadoEvaluacion();
        r1.setNota(10.0);

        ResultadoEvaluacion r2 = new ResultadoEvaluacion();
        r2.setNota(20.0);

        when(repositorio.findAll()).thenReturn(List.of(r1, r2));

        Double promedio = service.calcularPromedio(1L);

        assertEquals(15.0, promedio);
    }
}
