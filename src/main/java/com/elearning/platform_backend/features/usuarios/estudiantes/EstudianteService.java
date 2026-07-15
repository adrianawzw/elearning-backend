package com.elearning.platform_backend.features.usuarios.estudiantes;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public Estudiante buscarPorId(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    public Estudiante actualizar(Long id, EstudianteUpdateDTO dto) {
        Estudiante estudiante = buscarPorId(id);
        if (dto.nombres() != null) estudiante.setNombres(dto.nombres());
        if (dto.apellidos() != null) estudiante.setApellidos(dto.apellidos());
        return estudianteRepository.save(estudiante);
    }
}
