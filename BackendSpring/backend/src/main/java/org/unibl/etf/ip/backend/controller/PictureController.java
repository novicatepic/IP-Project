package org.unibl.etf.ip.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.unibl.etf.ip.backend.errorservice.ForbiddenEntity;
import org.unibl.etf.ip.backend.exceptions.MethodNotAllowedException;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.exceptions.ProgramTerminatedException;
import org.unibl.etf.ip.backend.loginservice.UserLoginHelp;
import org.unibl.etf.ip.backend.model.MessageModel;
import org.unibl.etf.ip.backend.service.FitnessProgramService;
import org.unibl.etf.ip.backend.service.PictureService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/pictures")
public class PictureController {

    //private static final String UPLOAD_DIR = "uploads";

    @Value("${upload.directory}")
    private String UPLOAD_DIR;

    @Value("${download.url}")
    private String DOWNLOAD_URL;

    @Autowired
    private PictureService fileStorageService;

    @Autowired
    private FitnessProgramService fitnessProgramService;

    @PostMapping("/upload/{programId}/{userId}")
    public ResponseEntity<MessageModel> uploadFile(
            @RequestParam("file") MultipartFile file,
            @PathVariable("programId") Integer programId,
            @PathVariable("userId") Integer userId)
            throws IOException, NotFoundException, MethodNotAllowedException, ProgramTerminatedException {

        if(fitnessProgramService.checkIfTerminated(programId)) {
            throw new ProgramTerminatedException();
        }

        if(!UserLoginHelp.checkUserValidity(userId)) {
            return ForbiddenEntity.returnForbidden();
        }

        String fileName = fileStorageService.saveFile(file, programId, userId);

        // Build the file download URI
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DOWNLOAD_URL+programId.toString()+"/")
                .path(fileName)
                .toUriString();

        MessageModel m = new MessageModel();
        m.setText("File uploaded successfully");
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @GetMapping("/{programId}")
    public ResponseEntity<List<String>> readPhotos(
            @PathVariable("programId") Integer programId) {
        String directoryPath = UPLOAD_DIR + File.separator + programId;
        List<String> photoUrls = fileStorageService.getPhotoUrls(directoryPath, programId);
        return new ResponseEntity<>(photoUrls, HttpStatus.OK);
    }

}
