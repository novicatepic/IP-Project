package org.unibl.etf.ip.backend.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.auth.AuthenticationController;
import org.unibl.etf.ip.backend.errorservice.ForbiddenEntity;
import org.unibl.etf.ip.backend.exceptions.MethodNotAllowedException;
import org.unibl.etf.ip.backend.exceptions.NotEnoughMoneyException;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.loginservice.UserLoginHelp;
import org.unibl.etf.ip.backend.model.KorisnikPretplacenProgramEntity;
import org.unibl.etf.ip.backend.model.MessageModel;
import org.unibl.etf.ip.backend.model.ProgramEntity;
import org.unibl.etf.ip.backend.service.FitnessProgramService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/fitness-programs")
public class FitnessProgramController {

    @Autowired
    private FitnessProgramService service;

    Logger logger = LoggerFactory.getLogger(FitnessProgramController.class);

    @GetMapping
    public ResponseEntity<List<ProgramEntity>> getPrograms() {
        return new ResponseEntity<>(service.getPrograms(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramEntity> getProgram(@PathVariable("id") Integer id) throws NotFoundException {

        logger.info("Program info for program with id " + id + " requested!");

        return new ResponseEntity<>(service.getProgramById(id), HttpStatus.OK);
    }

    @GetMapping("/my-programs/{id}")
    public ResponseEntity<List<ProgramEntity>> getMyPrograms(@PathVariable("id") Integer id) {

        if(!UserLoginHelp.checkUserValidity(id)) {
            return ForbiddenEntity.returnForbidden();
        }

        logger.info("User with id " + id + " requested his programs!");

        return new ResponseEntity<>(service.getMyPrograms(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProgramEntity> createProgram(@Valid @RequestBody ProgramEntity fitnessProgram) {
        Integer userId = fitnessProgram.getKreatorId();
        if(!UserLoginHelp.checkUserValidity(userId)) {
            return ForbiddenEntity.returnForbidden();
        }

        logger.info("User with id " + userId + " tried to create program with name " + fitnessProgram.getNaziv());

         return new ResponseEntity<>(service.createProgram(fitnessProgram), HttpStatus.OK);
    }

    @DeleteMapping("/{programId}/{userId}")
    public ResponseEntity<MessageModel> deleteProgram(@PathVariable("programId") Integer programId,
                                                @PathVariable("userId") Integer userId) throws NotFoundException, MethodNotAllowedException {
        if(!UserLoginHelp.checkUserValidity(userId)) {
            return ForbiddenEntity.returnForbidden();
        }


        service.deleteProgram(programId, userId);

        MessageModel messageModel = new MessageModel("Successfully deleted program!");

        logger.info("User with id " + userId + " deleted program with id " + programId);

        return new ResponseEntity<>(messageModel, HttpStatus.OK);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<KorisnikPretplacenProgramEntity> subscribeToAProgram(@Valid @RequestBody KorisnikPretplacenProgramEntity subscribeEntity)
            throws NotFoundException, NotEnoughMoneyException {

        Integer userId = subscribeEntity.getKorisnikId();
        if(!UserLoginHelp.checkUserValidity(userId)) {
            return ForbiddenEntity.returnForbidden();
        }

        logger.info("User with id " + userId + " wants to subscribe to a program with id " + subscribeEntity.getProgramId());

        return new ResponseEntity<>(service.subscribeToAProgram(subscribeEntity), HttpStatus.OK);
    }

    @GetMapping("/upcoming-user-programs/{userId}")
    public ResponseEntity<List<ProgramEntity>> getUserParticipations(@PathVariable("userId")Integer userId) {
        if(!UserLoginHelp.checkUserValidity(userId)) {
            return ForbiddenEntity.returnForbidden();
        }

        logger.info("User with id " + userId + " checked upcoming programs!");

        return new ResponseEntity<>(service.getUserParticipations(userId), HttpStatus.OK);
    }

    @GetMapping("/past-user-programs/{userId}")
    public ResponseEntity<List<ProgramEntity>> getPastUserParticipations(@PathVariable("userId")Integer userId) {
        if(!UserLoginHelp.checkUserValidity(userId)) {
            return ForbiddenEntity.returnForbidden();
        }

        logger.info("User with id " + userId + " checked past program participations!");

        return new ResponseEntity<>(service.getPastUserParticipations(userId), HttpStatus.OK);
    }

    @GetMapping("/unparticipated-user-programs/{userId}")
    public ResponseEntity<List<ProgramEntity>> getUserUnparticipated(@PathVariable("userId")Integer userId) {
        if(!UserLoginHelp.checkUserValidity(userId)) {
            return ForbiddenEntity.returnForbidden();
        }

        logger.info("User with id " + userId + " checked unparticipated programs!");

        return new ResponseEntity<>(service.getUserUnparticipations(userId), HttpStatus.OK);
    }

}
