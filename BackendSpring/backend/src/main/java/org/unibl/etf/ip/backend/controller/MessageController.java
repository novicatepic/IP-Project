package org.unibl.etf.ip.backend.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.ip.backend.model.SavjetnikPorukaEntity;
import org.unibl.etf.ip.backend.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService service;

    @PostMapping
    public ResponseEntity<SavjetnikPorukaEntity> messageConsultant(@RequestBody SavjetnikPorukaEntity message) {
        return new ResponseEntity<SavjetnikPorukaEntity>(service.messageConsultant(message), HttpStatus.OK);
    }

    @PostMapping("/message-user")
    public ResponseEntity<SavjetnikPorukaEntity> messageUser(@RequestBody SavjetnikPorukaEntity message) {
        return new ResponseEntity<SavjetnikPorukaEntity>(service.messageConsultant(message), HttpStatus.OK);
    }

}
