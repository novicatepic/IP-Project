package org.unibl.etf.ip.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.unibl.etf.ip.backend.service.PictureService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/pictures")
public class PictureController {

    private static final String UPLOAD_DIR = "uploads";

    @Autowired
    private PictureService fileStorageService;

    @PostMapping("/upload/{programId}")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file, @PathVariable("programId") Integer programId) throws IOException {
        String fileName = fileStorageService.saveFile(file, programId);

        // Build the file download URI
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/download/")
                .path(fileName)
                .toUriString();

        return new ResponseEntity<>("File uploaded", HttpStatus.OK);
    }

    @GetMapping("/{programId}")
    public ResponseEntity<List<String>> readPhotos(
            @PathVariable("programId") Integer programId) {
        String directoryPath = UPLOAD_DIR + File.separator + programId;
        List<String> photoUrls = fileStorageService.getPhotoUrls(directoryPath);
        return new ResponseEntity<>(photoUrls, HttpStatus.OK);
    }

}
