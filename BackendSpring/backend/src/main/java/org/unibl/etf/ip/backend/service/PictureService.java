package org.unibl.etf.ip.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PictureService {

    private static final String UPLOAD_DIR = "uploads";

    public String saveFile(MultipartFile file, Integer programId) throws IOException {
        //System.out.println("File " + file.toString());

        String directoryPath = UPLOAD_DIR+ File.separator+programId;
        File dir = new File(directoryPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(directoryPath).resolve(fileName);
        //System.out.println("IN..");
        Files.copy(file.getInputStream(), filePath);
        //System.out.println("IN...");
        return fileName;
    }

    public List<String> getPhotoUrls(String directoryPath) {
        File directory = new File(directoryPath);
        List<String> photoUrls = new ArrayList<>();

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    photoUrls.add(ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/api/files/download/")
                            .path(file.getName())
                            .toUriString());
                }
            }
        }

        return photoUrls;
    }

}
