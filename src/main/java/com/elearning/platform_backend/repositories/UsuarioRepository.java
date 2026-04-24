package com.elearning.platform_backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.elearning.platform_backend.entities.Rol;
import com.elearning.platform_backend.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
        
        List<Usuario> findByRol(Rol rol);
        Optional<Usuario> findByEmail(String email);
        List<Usuario> findByNombre(String nombre);
}
