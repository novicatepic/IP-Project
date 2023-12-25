package org.unibl.etf.ip.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.model.PitanjeEntity;
import org.unibl.etf.ip.backend.model.SavjetnikPorukaEntity;
import org.unibl.etf.ip.backend.service.QuestionService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @GetMapping
    @RequestMapping("/{programId}")
    public ResponseEntity<List<PitanjeEntity>> getQuestionsByProgram(@PathVariable("programId")Integer programId) throws Exception {
        return new ResponseEntity<>(service.getProgramById(programId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PitanjeEntity> createQuestion(@RequestBody PitanjeEntity question) {
        System.out.println("IN");
        return new ResponseEntity<>(service.createQuestion(question), HttpStatus.OK);
    }

    @PostMapping("/consultants")
    public ResponseEntity<SavjetnikPorukaEntity> createQuestionForConsultant(@RequestBody SavjetnikPorukaEntity question) {
        return new ResponseEntity<>(service.createQuestionForConsultant(question), HttpStatus.OK);
    }

    @PostMapping("/respond")
    public ResponseEntity<PitanjeEntity> respondToQuestion(@RequestBody PitanjeEntity question) {
        return new ResponseEntity<>(service.respondToAQuestion(question), HttpStatus.OK);
    }
}
