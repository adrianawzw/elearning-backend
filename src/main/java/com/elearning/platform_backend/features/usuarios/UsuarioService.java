package com.elearning.platform_backend.features.usuarios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final DocenteRepository docenteRepository;
    private final EstudianteRepository estudianteRepository;

    @Transactional
    public UsuarioReaderDTO create(UsuarioWriterDTO dto) {
        if (usuarioRepository.findByEmail(dto.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya está registrado");
        }

        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuario.setFechaRegistro(LocalDateTime.now());
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        if (dto.rol() == Rol.DOCENTE) {
            Docente docente = new Docente();
            docente.setUsuario(usuarioGuardado);
            docente.setNombres(dto.nombres());
            docente.setApellidos(dto.apellidos());
            docente.setEspecialidad(dto.especialidad());
            docenteRepository.save(docente);
        } else {
            Estudiante estudiante = new Estudiante();
            estudiante.setUsuario(usuarioGuardado);
            estudiante.setNombres(dto.nombres());
            estudiante.setApellidos(dto.apellidos());
            estudiante.setCodigoAlumno(dto.codigoAlumno());
            estudianteRepository.save(estudiante);
        }

        return findById(usuarioGuardado.getId());
    }

    public UsuarioReaderDTO findById(Long id) {
        Usuario user = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Docente docente = docenteRepository.findById(id).orElse(null);
        Estudiante estudiante = (docente == null) ? estudianteRepository.findById(id).orElse(null) : null;

        return UsuarioMapper.toDto(user, docente, estudiante);
    }

    public List<UsuarioReaderDTO> findAll() {
        if (usuarioRepository.count() == 0) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay usuarios registrados");
        
        return usuarioRepository.findAll().stream()
                .map(user -> findById(user.getId()))
                .toList();
    }

    public void deleteById(Long id) throws Exception {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no existe");
        }
        usuarioRepository.deleteById(id);
    }

    public UsuarioReaderDTO update(Long id, UsuarioUpdateDTO dto) throws Exception {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (usuario.getRol() == Rol.DOCENTE) {
            Docente docente = docenteRepository.findById(id)
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil docente no encontrado"));
            docente.setNombres(dto.nombres());
            docente.setApellidos(dto.apellidos());
            docente.setEspecialidad(dto.especialidad());
            docenteRepository.save(docente);
        } else if (usuario.getRol() == Rol.ESTUDIANTE) {
            Estudiante estudiante = estudianteRepository.findById(id)
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil estudiante no encontrado"));
            estudiante.setNombres(dto.nombres());
            estudiante.setApellidos(dto.apellidos());
            estudiante.setCodigoAlumno(dto.codigoAlumno());
            estudianteRepository.save(estudiante);
        }

        return findById(id);
    }
}
