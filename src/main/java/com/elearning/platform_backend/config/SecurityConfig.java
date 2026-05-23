package com.elearning.platform_backend.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.elearning.platform_backend.security.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtFilter;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/api/v1/auth/**")
                        .permitAll()
                        // USUARIOS
                        // SOLO DOCENTE
                        .requestMatchers(HttpMethod.GET, "/api/v1/usuarios").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/usuarios/**").hasRole("DOCENTE")
                        // DOCENTE Y ESTUDIANTE
                        .requestMatchers(HttpMethod.POST,"/api/v1/usuarios/**").hasAnyRole("DOCENTE", "ESTUDIANTE")
                        .requestMatchers(HttpMethod.GET, "/api/v1/usuarios/**").hasAnyRole("DOCENTE", "ESTUDIANTE")
                        .requestMatchers(HttpMethod.PUT,"/api/v1/usuarios/**").hasAnyRole("DOCENTE","ESTUDIANTE")
                        // CURSOS
                        .requestMatchers(HttpMethod.GET, "/api/v1/cursos/**").hasAnyRole("DOCENTE", "ESTUDIANTE")
                        .requestMatchers(HttpMethod.POST, "/api/v1/cursos/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/cursos/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/cursos/**").hasRole("DOCENTE")
                        // CONTENIDOS
                        .requestMatchers(HttpMethod.GET, "/api/v1/contenidos/**").hasAnyRole("DOCENTE", "ESTUDIANTE")
                        .requestMatchers(HttpMethod.POST, "/api/v1/contenidos/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/contenidos/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/contenidos/**").hasRole("DOCENTE")
                        // EVALUACIONES
                        .requestMatchers(HttpMethod.GET, "/api/v1/evaluaciones/**").hasAnyRole("DOCENTE", "ESTUDIANTE")
                        .requestMatchers(HttpMethod.POST, "/api/v1/evaluaciones/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/evaluaciones/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/evaluaciones/**").hasRole("DOCENTE")
                        // INSCRIPCIONES
                        // SOLO DOCENTE
                        .requestMatchers(HttpMethod.GET, "/api/v1/inscripciones").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.GET, "/api/v1/inscripciones/estado/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/inscripciones/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/inscripciones/**").hasRole("DOCENTE")
                        // DOCENTE Y ESTUDIANTE
                        .requestMatchers(HttpMethod.GET, "/api/v1/inscripciones/estudiante/**")
                        .hasAnyRole("DOCENTE", "ESTUDIANTE")
                        .requestMatchers(HttpMethod.GET, "/api/v1/inscripciones/curso/**")
                        .hasAnyRole("DOCENTE", "ESTUDIANTE")
                        // SOLO ESTUDIANTE
                        .requestMatchers(HttpMethod.POST, "/api/v1/inscripciones/**").hasRole("ESTUDIANTE")

                        .anyRequest()
                        .authenticated())

                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
