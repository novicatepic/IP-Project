package org.unibl.etf.ip.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.ip.backend.model.ExerciseAPI;
import org.unibl.etf.ip.backend.service.ExerciseService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/exercises")
public class ExerciseAPIController {

    @Autowired
    private ExerciseService service;

    @GetMapping
    public ResponseEntity<List<ExerciseAPI>> responseEntity() {
       return new ResponseEntity<>(service.processApiResponse(), HttpStatus.OK);
    }
}
