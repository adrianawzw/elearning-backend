package com.elearning.platform_backend.features.usuarios;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.elearning.platform_backend.features.inscripciones.Inscripcion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "estudiantes")
@Data
public class Estudiante {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

    private String nombres;
    private String apellidos;
    private String codigoAlumno; // Atributo único de estudiante

    @OneToMany(mappedBy = "estudiante")
    @JsonIgnoreProperties("estudiante")
    private List<Inscripcion> inscripciones;
}
