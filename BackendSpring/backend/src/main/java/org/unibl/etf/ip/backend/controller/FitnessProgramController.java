package org.unibl.etf.ip.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.exceptions.MethodNotAllowedException;
import org.unibl.etf.ip.backend.exceptions.NotEnoughMoneyException;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.model.KorisnikPretplacenProgramEntity;
import org.unibl.etf.ip.backend.model.ProgramEntity;
import org.unibl.etf.ip.backend.service.FitnessProgramService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/fitness-programs")
public class FitnessProgramController {

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
    public ResponseEntity<ProgramEntity> createProgram(@RequestBody ProgramEntity fitnessProgram) {
        try {
            System.out.println("In fitness programs addition");
            return new ResponseEntity<>(service.createProgram(fitnessProgram), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{programId}/{userId}")
    public ResponseEntity<String> deleteProgram(@PathVariable("programId") Integer programId,
                                                @PathVariable("userId") Integer userId) throws NotFoundException, MethodNotAllowedException {
        System.out.println("In");
        service.deleteProgram(programId, userId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<KorisnikPretplacenProgramEntity> subscribeToAProgram(@RequestBody KorisnikPretplacenProgramEntity subscribeEntity)
            throws NotFoundException, NotEnoughMoneyException {
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
