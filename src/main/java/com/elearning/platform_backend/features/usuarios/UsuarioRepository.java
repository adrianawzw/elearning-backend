package com.elearning.platform_backend.features.usuarios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
        
        List<Usuario> findByRol(Rol rol);
        Optional<Usuario> findByEmail(String email);
        
}
