package com.example.steam2.managers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@RestController
@RequestMapping("/upload")
public class FileUploadManager {

    private static final Path HOME_DIR =
            Paths.get(System.getProperty("user.home"));

    private static final Path UPLOAD_DIR =
            HOME_DIR.resolve("uploads");

    private static final Path ACTIVE_FILE =
            HOME_DIR.resolve("active-file.txt");

    @PostMapping
    public synchronized ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        // Enforce MP3
        if (!"audio/mpeg".equalsIgnoreCase(file.getContentType())) {
            return ResponseEntity
                    .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body("Only MP3 files allowed");
        }

        String filename = sanitize(file.getOriginalFilename());
        if (filename == null || !filename.toLowerCase().endsWith(".mp3")) {
            return ResponseEntity
                    .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body("Invalid file name");
        }

        try {
            Files.createDirectories(UPLOAD_DIR);

            // Remove previous file
            deleteOldFile();

            // Save new file
            Path newFile = UPLOAD_DIR.resolve(filename);
            Files.copy(file.getInputStream(), newFile,
                    StandardCopyOption.REPLACE_EXISTING);

            // Store filename
            Files.writeString(
                    ACTIVE_FILE,
                    filename,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

            return ResponseEntity.ok("Uploaded to home: " + newFile);

        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed");
        }
    }

    private void deleteOldFile() throws IOException {
        if (Files.exists(ACTIVE_FILE)) {
            String oldFilename = Files.readString(ACTIVE_FILE).trim();
            if (!oldFilename.isEmpty()) {
                Path oldFile = UPLOAD_DIR.resolve(oldFilename);
                Files.deleteIfExists(oldFile);
            }
        }
    }

    private String sanitize(String filename) {
        if (filename == null) return null;
        // Prevent ../../ attacks
        return Paths.get(filename).getFileName().toString();
    }
}
