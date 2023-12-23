package org.unibl.etf.ip.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.model.PorukaEntity;
import org.unibl.etf.ip.backend.service.MessageUserService;

import java.util.List;

@RestController
@RequestMapping("/user-messages")
public class MessageUserController {

    @Autowired
    private MessageUserService service;

    @PostMapping
    public ResponseEntity<PorukaEntity> createMessage(@RequestBody PorukaEntity message) {
        return new ResponseEntity<PorukaEntity>(service.createUserMessage(message), HttpStatus.OK);
    }

    @GetMapping("/unread/{id}")
    public ResponseEntity<List<PorukaEntity>> unreadMessages(@PathVariable("id")Integer id) {
        return new ResponseEntity<List<PorukaEntity>>(service.readUnreadMessages(id), HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<PorukaEntity>> allMessages(@PathVariable("id")Integer id) {
        return new ResponseEntity<List<PorukaEntity>>(service.readMessages(id), HttpStatus.OK);
    }

}
