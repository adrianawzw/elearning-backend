package com.elearning.platform_backend.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.elearning.platform_backend.features.usuarios.UsuarioReaderDTO;
import com.elearning.platform_backend.features.usuarios.UsuarioService;
import com.elearning.platform_backend.features.usuarios.UsuarioWriterDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailService userDetailService;
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginDTO dto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }

        CustomUserDetail userDetail = (CustomUserDetail) userDetailService.loadUserByUsername(dto.email());
        String token = jwtService.generateToken(userDetail);
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioReaderDTO> registro(@Valid @RequestBody UsuarioWriterDTO dto) {
        return new ResponseEntity<>(usuarioService.create(dto), HttpStatus.CREATED);
    }
}
