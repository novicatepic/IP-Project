package org.unibl.etf.ip.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.model.SavjetnikPorukaEntity;
import org.unibl.etf.ip.backend.service.ConsultantMessageService;

@RestController
@RequestMapping("/messages")
public class ConsultantMessageController {

    @Autowired
    private ConsultantMessageService service;

    @GetMapping

    @PostMapping
    public ResponseEntity<SavjetnikPorukaEntity> messageConsultant(@RequestBody SavjetnikPorukaEntity message) {
        return new ResponseEntity<>(service.messageConsultant(message), HttpStatus.OK);
    }

    @PostMapping("/message-user")
    public ResponseEntity<SavjetnikPorukaEntity> messageUser(@RequestBody SavjetnikPorukaEntity message) {
        return new ResponseEntity<>(service.messageConsultant(message), HttpStatus.OK);
    }

}
