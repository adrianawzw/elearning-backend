package com.elearning.platform_backend.features.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.elearning.platform_backend.features.usuarios.UsuarioReaderDTO;
import com.elearning.platform_backend.security.AuthService;
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
