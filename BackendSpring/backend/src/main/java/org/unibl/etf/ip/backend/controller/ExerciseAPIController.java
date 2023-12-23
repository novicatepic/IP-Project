package org.unibl.etf.ip.backend.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.ip.backend.service.ExerciseService;

@RequestMapping("/api/exercises")
@RestController
public class ExerciseAPIController {

    @Autowired
    private ExerciseService service;

    @GetMapping
    public void responseEntity() throws Exception {
        service.processApiResponse();
    }

}
