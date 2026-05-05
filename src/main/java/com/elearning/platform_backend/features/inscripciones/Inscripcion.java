package com.elearning.platform_backend.features.inscripciones;

import java.time.LocalDateTime;
import java.util.List;

import com.elearning.platform_backend.features.cursos.Curso;
import com.elearning.platform_backend.features.usuarios.Estudiante;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
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
@Table(name = "inscripcion")
@Data
@NoArgsConstructor
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne 
    @JoinColumn(name = "estudiante_id")
    @JsonIgnoreProperties({ "cursos", "inscripciones" })
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    @JsonIgnoreProperties({ "contenidos", "evaluaciones", "inscripciones", "docente" })
    private Curso curso;

    private LocalDateTime fechaInscripcion;

    private String estado; // ACTIVO o FINALIZADO

    private Double notaFinal;

    @OneToMany(mappedBy = "inscripcion")
    @JsonIgnore
    private List<Progreso> progresos;
}
