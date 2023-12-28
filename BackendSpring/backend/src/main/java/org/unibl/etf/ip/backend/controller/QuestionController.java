package org.unibl.etf.ip.backend.controller;

import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.errorservice.ForbiddenEntity;
import org.unibl.etf.ip.backend.loginservice.UserLoginHelp;
import org.unibl.etf.ip.backend.model.OdgovorEntity;
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

    @GetMapping("/{programId}")
    public ResponseEntity<List<PitanjeEntity>> getQuestionsByProgram(@PathVariable("programId")Integer programId) throws Exception {
        return new ResponseEntity<>(service.getProgramById(programId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PitanjeEntity> createQuestion(@RequestBody PitanjeEntity question) {
        if(!UserLoginHelp.checkUserValidity(question.getKorisnikId())) {
            return ForbiddenEntity.returnForbidden();
        }
        return new ResponseEntity<>(service.createQuestion(question), HttpStatus.OK);
    }

    @PostMapping("/consultants")
    public ResponseEntity<SavjetnikPorukaEntity> createQuestionForConsultant(@RequestBody SavjetnikPorukaEntity question) {
        if(!UserLoginHelp.checkUserValidity(question.getKorisnikId())) {
            return ForbiddenEntity.returnForbidden();
        }
        return new ResponseEntity<>(service.createQuestionForConsultant(question), HttpStatus.OK);
    }

    @PostMapping("/respond")
    public ResponseEntity<OdgovorEntity> respondToQuestion(@RequestBody OdgovorEntity response) {
        if(!UserLoginHelp.checkUserValidity(response.getKorisnikId())) {
            return ForbiddenEntity.returnForbidden();
        }
        return new ResponseEntity<>(service.respondToAQuestion(response), HttpStatus.OK);
    }
}
