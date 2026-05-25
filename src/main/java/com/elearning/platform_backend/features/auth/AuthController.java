package com.elearning.platform_backend.features.auth;

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
import com.elearning.platform_backend.security.AuthService;
import com.elearning.platform_backend.security.CustomUserDetail;
import com.elearning.platform_backend.security.CustomUserDetailService;
import com.elearning.platform_backend.security.JwtService;
import com.elearning.platform_backend.security.LoginDTO;
import com.elearning.platform_backend.util.AuthRequest;
import com.elearning.platform_backend.util.AuthResponse;
import com.elearning.platform_backend.util.RegisterRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UsuarioReaderDTO> register(@Valid @RequestBody RegisterRequest request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(
            @Valid @RequestBody AuthRequest request) {

        return ResponseEntity.ok(authService.authenticate(request));
    }

}
