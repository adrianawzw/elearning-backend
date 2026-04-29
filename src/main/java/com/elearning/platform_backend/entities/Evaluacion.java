package com.elearning.platform_backend.entities;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evaluacion")
@Data
@NoArgsConstructor
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Enumerated(EnumType.STRING)
    private TipoEvaluacion tipo;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    @JsonIgnoreProperties({ "contenidos", "evaluaciones", "inscripciones" })
    private Curso curso;

    private Integer puntajeMinimo;

    @OneToMany(mappedBy = "evaluacion")
    @JsonIgnoreProperties("evaluacion")
    private List<Pregunta> preguntas;
}
