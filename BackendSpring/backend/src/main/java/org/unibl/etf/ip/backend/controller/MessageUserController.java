package org.unibl.etf.ip.backend.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.errorservice.ForbiddenEntity;
import org.unibl.etf.ip.backend.exceptions.MethodNotAllowedException;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.loginservice.UserLoginHelp;
import org.unibl.etf.ip.backend.model.PorukaEntity;
import org.unibl.etf.ip.backend.service.MessageUserService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/user-messages")
public class MessageUserController {

    @Autowired
    private MessageUserService service;

    Logger logger = LoggerFactory.getLogger(MessageUserController.class);

    @PostMapping
    public ResponseEntity<PorukaEntity> createMessage(@Valid @RequestBody PorukaEntity message) {
        if(!UserLoginHelp.checkUserValidity(message.getPosiljalacId())) {
            return ForbiddenEntity.returnForbidden();
        }

        logger.info("User with id " + message.getPosiljalacId() + " messaged user with id " + message.getPrimalacId());

        return new ResponseEntity<>(service.createUserMessage(message), HttpStatus.OK);
    }

    @GetMapping("/unread/{id}")
    public ResponseEntity<List<PorukaEntity>> unreadMessages(@PathVariable("id")Integer id) {
        if(!UserLoginHelp.checkUserValidity(id)) {
            return ForbiddenEntity.returnForbidden();
        }

        logger.info("User with id " + id + " checked his unread messages!");

        return new ResponseEntity<>(service.readUnreadMessages(id), HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<PorukaEntity>> allMessages(@PathVariable("id")Integer id) {
        if(!UserLoginHelp.checkUserValidity(id)) {
            return ForbiddenEntity.returnForbidden();
        }

        logger.info("User with id " + id + " checked all of his messages!");

        return new ResponseEntity<>(service.readMessages(id), HttpStatus.OK);
    }

    @GetMapping("/{messageId}/{userId}")
    public ResponseEntity<PorukaEntity> getOneMessage(@PathVariable("messageId")Integer messageId,
                                                      @PathVariable("userId") Integer userId) throws NotFoundException, MethodNotAllowedException {
        PorukaEntity message = service.showMessage(messageId);
        if(message == null) {
            throw new NotFoundException();
        }
        if(message.getPrimalacId() != userId) {
            throw new MethodNotAllowedException();
        }

        logger.info("User with id " + userId + " checked message with id " + messageId);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/read-message/{id}/{userId}")
    public ResponseEntity<PorukaEntity> readMessage(@PathVariable("id")Integer id,
                                                    @PathVariable("userId")Integer userId) throws NotFoundException {
        return new ResponseEntity<>(service.readMessage(id, userId), HttpStatus.OK);
    }

}
