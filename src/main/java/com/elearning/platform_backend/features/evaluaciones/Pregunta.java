package com.elearning.platform_backend.features.evaluaciones;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pregunta")
@Data
@NoArgsConstructor
public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String enunciado;

    private String opciones; // puedes guardar como texto o JSON

    private Integer valorPunto;

    private String respuestaCorrecta;

    @ManyToOne
    @JoinColumn(name = "evaluacion_id")
    @JsonIgnoreProperties("preguntas")
    private Evaluacion evaluacion;
}
