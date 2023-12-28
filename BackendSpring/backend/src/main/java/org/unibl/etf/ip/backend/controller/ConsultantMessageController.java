package org.unibl.etf.ip.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.errorservice.ForbiddenEntity;
import org.unibl.etf.ip.backend.loginservice.UserLoginHelp;
import org.unibl.etf.ip.backend.model.SavjetnikPorukaEntity;
import org.unibl.etf.ip.backend.service.ConsultantMessageService;

@CrossOrigin("*")
@RestController
@RequestMapping("/messages")
public class ConsultantMessageController {

    @Autowired
    private ConsultantMessageService service;


    @PostMapping
    public ResponseEntity<SavjetnikPorukaEntity> messageConsultant(@RequestBody SavjetnikPorukaEntity message) {

        if(!UserLoginHelp.checkUserValidity(message.getKorisnikId())) {
            return ForbiddenEntity.returnForbidden();
        }

        return new ResponseEntity<>(service.messageConsultant(message), HttpStatus.OK);
    }

    @PostMapping("/message-user")
    public ResponseEntity<SavjetnikPorukaEntity> messageUser(@RequestBody SavjetnikPorukaEntity message) {
        if(!UserLoginHelp.checkUserValidity(message.getKorisnikId())) {
            return ForbiddenEntity.returnForbidden();
        }

        return new ResponseEntity<>(service.messageConsultant(message), HttpStatus.OK);
    }

}
