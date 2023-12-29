package org.unibl.etf.ip.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.model.LokacijaEntity;
import org.unibl.etf.ip.backend.service.LokacijaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/locations")
public class LokacijaController {

    @Autowired
    private LokacijaService service;

    @PostMapping
    public ResponseEntity<LokacijaEntity> addLocation(@RequestBody LokacijaEntity location) {
        return new ResponseEntity(service.insertLocation(location), HttpStatus.OK);
    }

}
