package com.minidrive.minidrive.controller;

import com.minidrive.minidrive.dto.ApiResponse;
import com.minidrive.minidrive.model.FileInfo;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FileController {

    private static final String UPLOAD_DIR = "C:/minidrive/uploads";

    // ================= UPLOAD =================
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ApiResponse<String> upload(@RequestParam("file") MultipartFile file) {

        try {
            File dir = new File(UPLOAD_DIR);
            dir.mkdirs();

            File dest = new File(dir, file.getOriginalFilename());
            file.transferTo(dest);

            return new ApiResponse<>(true, "File uploaded successfully", dest.getName());

        } catch (Exception e) {
            return new ApiResponse<>(false, "Upload failed", null);
        }
    }

    // ================= LIST FILES =================
    @GetMapping("/files")
    public ApiResponse<List<FileInfo>> listFiles() {

        File folder = new File(UPLOAD_DIR);
        if (!folder.exists()) {
            return new ApiResponse<>(false, "Upload directory not found", List.of());
        }

        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            return new ApiResponse<>(true, "No files found", List.of());
        }

        List<FileInfo> result = Arrays.stream(files)
                .filter(File::isFile)
                .map(file -> new FileInfo(
                        file.getName(),
                        readableSize(file.length()),
                        "application/octet-stream",
                        LocalDateTime.ofInstant(
                                Instant.ofEpochMilli(file.lastModified()),
                                ZoneId.systemDefault()
                        )
                ))
                .toList();

        return new ApiResponse<>(true, "Files fetched successfully", result);
    }

    // ================= DOWNLOAD =================
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> download(@PathVariable String filename)
            throws MalformedURLException {

        Path path = Paths.get(UPLOAD_DIR).resolve(filename).normalize();
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // ================= DELETE =================
    @DeleteMapping("/delete/{filename}")
    public ApiResponse<String> delete(@PathVariable String filename) {

        File file = new File(UPLOAD_DIR, filename);

        if (!file.exists()) {
            return new ApiResponse<>(false, "File not found", null);
        }

        file.delete();
        return new ApiResponse<>(true, "File deleted successfully", filename);
    }

    // ================= RENAME =================
    @PutMapping("/rename")
    public ApiResponse<String> rename(
            @RequestParam String oldName,
            @RequestParam String newName) {

        File oldFile = new File(UPLOAD_DIR, oldName);
        File newFile = new File(UPLOAD_DIR, newName);

        if (!oldFile.exists()) {
            return new ApiResponse<>(false, "File not found", null);
        }

        oldFile.renameTo(newFile);
        return new ApiResponse<>(true, "File renamed successfully", newName);
    }

    // ================= UTILITY =================
    private String readableSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return (bytes / 1024) + " KB";
        return (bytes / (1024 * 1024)) + " MB";
    }
}
