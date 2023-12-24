package org.unibl.etf.ip.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.ip.backend.model.LogEntity;
import org.unibl.etf.ip.backend.service.LoggerService;

import java.io.IOException;

@RestController
@RequestMapping("/logger")
public class LoggerController {

    @Autowired
    private LoggerService service;

    @GetMapping
    public ResponseEntity<LogEntity> getLogFileData() throws IOException {
        return new ResponseEntity<>(service.getLogs(), HttpStatus.OK);
    }

}
