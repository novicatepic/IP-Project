package org.unibl.etf.ip.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.model.KorisnikPretplacenProgramEntity;
import org.unibl.etf.ip.backend.model.ProgramEntity;
import org.unibl.etf.ip.backend.service.FitnessProgramService;

import java.util.List;


@RestController
@RequestMapping("/fitness-programs")
public class FitnessProgramController {

    Logger logger = LoggerFactory.getLogger(FitnessProgramController.class);

    @Autowired
    private FitnessProgramService service;

    @GetMapping
    public ResponseEntity<List<ProgramEntity>> getPrograms() {
        return new ResponseEntity<>(service.getPrograms(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramEntity> getProgram(@PathVariable("id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(service.getProgramById(id), HttpStatus.OK);
    }

    @GetMapping("/my-programs/{id}")
    public ResponseEntity<List<ProgramEntity>> getMyPrograms(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.getMyPrograms(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProgramEntity> createProgram(@RequestBody ProgramEntity fitnessProgram) throws Exception {
        return new ResponseEntity<>(service.createProgram(fitnessProgram), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProgram(@PathVariable("id") Integer id) throws Exception {
        service.deleteProgram(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<KorisnikPretplacenProgramEntity> subscribeToAProgram(@RequestBody KorisnikPretplacenProgramEntity subscribeEntity) {
        return new ResponseEntity<>(service.subscribeToAProgram(subscribeEntity), HttpStatus.OK);
    }

    @GetMapping("/upcoming-user-programs/{userId}")
    public ResponseEntity<List<ProgramEntity>> getUserParticipations(@PathVariable("userId")Integer userId) {
        return new ResponseEntity<>(service.getUserParticipations(userId), HttpStatus.OK);
    }

    @GetMapping("/past-user-programs/{userId}")
    public ResponseEntity<List<ProgramEntity>> getPastUserParticipations(@PathVariable("userId")Integer userId) {
        return new ResponseEntity<>(service.getPastUserParticipations(userId), HttpStatus.OK);
    }

    @GetMapping("/unparticipated-user-programs/{userId}")
    public ResponseEntity<List<ProgramEntity>> getUserUnparticipated(@PathVariable("userId")Integer userId) {
        return new ResponseEntity<>(service.getUserUnparticipations(userId), HttpStatus.OK);
    }

}
