package com.elearning.platform_backend.features.cursos;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import com.elearning.platform_backend.features.cursos.contenidos.SupabaseStorageService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

        private final CursoService cursoService;
        private final SupabaseStorageService storageService;

        @GetMapping("/publicos")
        public ResponseEntity<List<CursoReaderDTO>> getPublicos() {
                return ResponseEntity.ok(cursoService.findAllPublicos());
        }

        @GetMapping
        public ResponseEntity<List<CursoReaderDTO>> getAll() {
                return ResponseEntity.ok(cursoService.findAll());
        }

        @GetMapping("/docente/{id}")
        public List<CursoReaderDTO> obtenerPorDocente(@PathVariable Long id) {
                return cursoService.buscarPorDocente(id);
        }

        @GetMapping("/{id}")
        public ResponseEntity<CursoReaderDTO> getById(@PathVariable Long id) {
                return ResponseEntity.ok(cursoService.findById(id));
        }

        @PostMapping
        public ResponseEntity<CursoReaderDTO> create(@RequestBody CursoWriterDTO curso) {
                return new ResponseEntity<>(cursoService.create(curso), HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<CursoReaderDTO> update(
                        @PathVariable Long id,
                        @RequestBody CursoUpdateDTO curso) throws Exception {
                return ResponseEntity.ok(cursoService.update(id, curso));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
                cursoService.deleteById(id);
                return ResponseEntity.noContent().build();
        }

        @PostMapping(value = "/upload-imagen", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<String> uploadImagen(@RequestParam("file") MultipartFile file) {
                try {
                        String url = storageService.uploadImage(file);
                        return ResponseEntity.ok(url);
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .body("Error al subir imagen: " + e.getMessage());
                }
        }
}
