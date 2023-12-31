package org.unibl.etf.ip.backend.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
@RestController
@RequestMapping("/api/files/download")
public class FileDownloadController {

    //private static final String UPLOAD_DIR = "uploads";

    @Value("${upload.directory}")
    private String UPLOAD_DIR;

    @GetMapping(value = "/{programId}/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, @PathVariable("programId") Integer programId)
            throws IOException {
        // Construct the file path
        Path filePath = Paths.get(UPLOAD_DIR + File.separator + programId + File.separator).resolve(fileName);

        // Create a Resource representing the file
        Resource fileResource = new FileSystemResource(filePath.toFile());

        // Return the file as the response with appropriate content type
        return ResponseEntity.ok()
                .contentLength(fileResource.contentLength())
                .contentType(MediaType.IMAGE_JPEG)
                .body(fileResource);
    }
}