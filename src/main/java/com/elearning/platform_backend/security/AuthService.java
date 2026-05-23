package com.elearning.platform_backend.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.elearning.platform_backend.features.usuarios.Rol;
import com.elearning.platform_backend.features.usuarios.Usuario;
import com.elearning.platform_backend.features.usuarios.UsuarioReaderDTO;
import com.elearning.platform_backend.features.usuarios.UsuarioRepository;
import com.elearning.platform_backend.features.usuarios.UsuarioService;
import com.elearning.platform_backend.features.usuarios.UsuarioWriterDTO;
import com.elearning.platform_backend.util.AuthRequest;
import com.elearning.platform_backend.util.AuthResponse;
import com.elearning.platform_backend.util.RegisterRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public UsuarioReaderDTO register(RegisterRequest request) {

        UsuarioWriterDTO dto = new UsuarioWriterDTO(
                request.email(),
                request.password(),
                request.nombres(),
                request.apellidos(),
                Rol.ESTUDIANTE,
                null);

        return usuarioService.create(dto);
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        Usuario usuario = usuarioRepository.findByEmail(request.email()).orElseThrow();

        String jwtToken = jwtService.generateToken(new CustomUserDetail(usuario));

        return new AuthResponse(jwtToken);
    }

}