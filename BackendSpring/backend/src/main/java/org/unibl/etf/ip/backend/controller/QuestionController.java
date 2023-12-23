package org.unibl.etf.ip.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.ip.backend.model.PitanjeEntity;
import org.unibl.etf.ip.backend.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @GetMapping
    @RequestMapping("/{programId}")
    public ResponseEntity<List<PitanjeEntity>> getQuestionsByProgram(@PathVariable("programId")Integer programId) throws Exception {
        return new ResponseEntity<List<PitanjeEntity>>(service.getProgramById(programId), HttpStatus.OK);
    }

}
