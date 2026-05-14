package com.elearning.platform_backend.features.cursos;

import java.time.LocalDateTime;
import java.util.List;

import com.elearning.platform_backend.features.evaluaciones.Evaluacion;
import com.elearning.platform_backend.features.inscripciones.Inscripcion;
import com.elearning.platform_backend.features.usuarios.Docente;
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
@Table(name = "curso")
@Data
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "docente_id")
    @JsonIgnoreProperties({ "cursos", "inscripciones" })
    private Docente docente;
    
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "curso")
    @JsonIgnoreProperties("curso")
    private List<Contenido> contenidos;

    @OneToMany(mappedBy = "curso")
    @JsonIgnoreProperties("curso")
    private List<Evaluacion> evaluaciones;

    @OneToMany(mappedBy = "curso")
    @JsonIgnoreProperties("curso")
    private List<Inscripcion> inscripciones;
}
