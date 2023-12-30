package org.unibl.etf.ip.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.unibl.etf.ip.backend.exceptions.MethodNotAllowedException;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.model.ProgramEntity;
import org.unibl.etf.ip.backend.repository.FitnessProgramRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PictureService {

    private Logger logger = LoggerFactory.getLogger(PictureService.class);

    @Autowired
    private FitnessProgramRepository repository;

    //private static final String UPLOAD_DIR = "uploads";

    @Value("${upload.directory}")
    private String UPLOAD_DIR;

    @Value("${download.url}")
    private String DOWNLOAD_URL;

    public String saveFile(MultipartFile file, Integer programId, Integer userId)
            throws IOException, NotFoundException, MethodNotAllowedException {
        //System.out.println("File " + file.toString());

        ProgramEntity programEntity = repository.findById(programId).orElseThrow(NotFoundException::new);

        if(programEntity.getKreatorId() != userId) {
            throw new MethodNotAllowedException();
        }

        String directoryPath = UPLOAD_DIR+ File.separator+programId;
        File dir = new File(directoryPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }

        String fileName =  file.getOriginalFilename();
        Path filePath = Paths.get(directoryPath).resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        logger.info("New file uploaded for program with id " + programId);
        return fileName;
    }

    public List<String> getPhotoUrls(String directoryPath, Integer programId) {
        File directory = new File(directoryPath);
        List<String> photoUrls = new ArrayList<>();

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    photoUrls.add(ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path(DOWNLOAD_URL+programId.toString()+"/")
                            .path(file.getName())
                            .toUriString());
                }
            }
        }
        logger.info("Photos read from program with id " + programId);
        return photoUrls;
    }

}
