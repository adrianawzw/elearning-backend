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
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // USUARIOS
                        // SOLO DOCENTE
                        .requestMatchers(HttpMethod.GET, "/usuarios").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.DELETE,"/usuarios/**").hasRole("DOCENTE")
                        // DOCENTE Y ESTUDIANTE
                        .requestMatchers(HttpMethod.POST,"/usuarios/**").hasAnyRole("DOCENTE", "ESTUDIANTE")
                        .requestMatchers(HttpMethod.GET, "/usuarios/**").hasAnyRole("DOCENTE", "ESTUDIANTE")
                        .requestMatchers(HttpMethod.PUT,"/usuarios/**").hasAnyRole("DOCENTE","ESTUDIANTE")
                        // CURSOS
                        .requestMatchers(HttpMethod.GET, "/cursos/**").hasAnyRole("DOCENTE", "ESTUDIANTE")
                        .requestMatchers(HttpMethod.POST, "/cursos/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.PUT, "/cursos/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.DELETE, "/cursos/**").hasRole("DOCENTE")
                        // CONTENIDOS
                        .requestMatchers(HttpMethod.GET, "/contenidos/**").hasAnyRole("DOCENTE", "ESTUDIANTE")
                        .requestMatchers(HttpMethod.POST, "/contenidos/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.PUT, "/contenidos/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.DELETE, "/contenidos/**").hasRole("DOCENTE")
                        // EVALUACIONES
                        .requestMatchers(HttpMethod.GET, "/evaluaciones/**").hasAnyRole("DOCENTE", "ESTUDIANTE")
                        .requestMatchers(HttpMethod.POST, "/evaluaciones/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.PUT, "/evaluaciones/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.DELETE, "/evaluaciones/**").hasRole("DOCENTE")
                        // INSCRIPCIONES
                        .requestMatchers(HttpMethod.GET, "/inscripciones").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.GET, "/inscripciones/estado/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.PATCH, "/inscripciones/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.DELETE, "/inscripciones/**").hasRole("DOCENTE")
                        .requestMatchers(HttpMethod.GET, "/inscripciones/estudiante/**").hasAnyRole("DOCENTE", "ESTUDIANTE")
                        .requestMatchers(HttpMethod.GET, "/inscripciones/curso/**").hasAnyRole("DOCENTE", "ESTUDIANTE")
                        .requestMatchers(HttpMethod.POST, "/inscripciones/**").hasRole("ESTUDIANTE")

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
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
