package com.elearning.platform_backend.features.cursos;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cursos")
@RequiredArgsConstructor
public class CursoController {

        private final CursoService cursoService;

        @GetMapping
        public ResponseEntity<List<CursoReaderDTO>> getAll() {
                return ResponseEntity.ok(
                                cursoService.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<CursoReaderDTO> getById(
                        @PathVariable Long id) {

                return ResponseEntity.ok(
                                cursoService.findById(id));
        }

        @PostMapping
        public ResponseEntity<CursoReaderDTO> create(
                        @RequestBody CursoWriterDTO curso) {

                return new ResponseEntity<>(
                                cursoService.create(curso),
                                HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<CursoReaderDTO> update(
                        @PathVariable Long id,
                        @RequestBody CursoUpdateDTO curso) throws Exception {

                return ResponseEntity.ok(
                                cursoService.update(id, curso));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(
                        @PathVariable Long id) throws Exception {

                cursoService.deleteById(id);

                return ResponseEntity
                                .noContent()
                                .build();
        }

        @GetMapping("/docente/{id}")
        public List<CursoReaderDTO> obtenerPorDocente(
                        @PathVariable Long id) {

                return cursoService.buscarPorDocente(id);
        }
}