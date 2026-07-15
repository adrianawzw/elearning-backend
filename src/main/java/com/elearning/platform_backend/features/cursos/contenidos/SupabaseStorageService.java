package com.elearning.platform_backend.features.cursos.contenidos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SupabaseStorageService {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.service-role-key}")
    private String serviceRoleKey;

    @Value("${supabase.storage.bucket}")
    private String bucket;

    private final RestTemplate restTemplate = new RestTemplate();

    public String uploadImage(MultipartFile file) throws IOException {
        return uploadImage(file, "cursos");
    }

    public String uploadImage(MultipartFile file, String prefix) throws IOException {
        String extension = getExtension(file.getOriginalFilename());
        String fileName = prefix + "/" + UUID.randomUUID() + extension;

        String uploadUrl = supabaseUrl + "/storage/v1/object/" + bucket + "/" + fileName;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + serviceRoleKey);
        String contentType = file.getContentType() != null ? file.getContentType() : "image/jpeg";
        headers.setContentType(MediaType.parseMediaType(contentType));

        HttpEntity<byte[]> entity = new HttpEntity<>(file.getBytes(), headers);
        restTemplate.exchange(uploadUrl, HttpMethod.POST, entity, String.class);

        return supabaseUrl + "/storage/v1/object/public/" + bucket + "/" + fileName;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String extension = getExtension(file.getOriginalFilename());
        String fileName = UUID.randomUUID() + extension;

        String uploadUrl = supabaseUrl + "/storage/v1/object/" + bucket + "/" + fileName;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + serviceRoleKey);
        String contentType = file.getContentType() != null ? file.getContentType() : "application/octet-stream";
        headers.setContentType(MediaType.parseMediaType(contentType));

        HttpEntity<byte[]> entity = new HttpEntity<>(file.getBytes(), headers);
        restTemplate.exchange(uploadUrl, HttpMethod.POST, entity, String.class);

        return supabaseUrl + "/storage/v1/object/public/" + bucket + "/" + fileName;
    }

    public void deleteFile(String fileUrl) {
        // Extrae el path después de "/public/{bucket}/"
        String prefix = "/storage/v1/object/public/" + bucket + "/";
        int idx = fileUrl.indexOf(prefix);
        if (idx == -1) return;
        String filePath = fileUrl.substring(idx + prefix.length());

        String deleteUrl = supabaseUrl + "/storage/v1/object/" + bucket + "/" + filePath;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + serviceRoleKey);
        try {
            restTemplate.exchange(deleteUrl, HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
        } catch (Exception e) {
            log.warn("No se pudo eliminar foto anterior de Supabase: {}", e.getMessage());
        }
    }

    private String getExtension(String filename) {
        if (filename == null || !filename.contains(".")) return "";
        return filename.substring(filename.lastIndexOf("."));
    }
}
