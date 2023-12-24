package org.unibl.etf.ip.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.model.KorisnikEntity;
import org.unibl.etf.ip.backend.service.FitnessUserService;

@RestController
@RequestMapping("/fitness-users")
public class FitnessUserController {

    @Autowired
    private FitnessUserService service;

    @PostMapping
    public ResponseEntity<KorisnikEntity> createFitnessUser(@RequestBody KorisnikEntity fitnessUser) {
        return new ResponseEntity<KorisnikEntity>(service.createFitnessUser(fitnessUser), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<KorisnikEntity> updateFitnessUser(@RequestBody KorisnikEntity fitnessUser) {
        return new ResponseEntity<KorisnikEntity>(service.updateFitnessUser(fitnessUser), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<KorisnikEntity> loginFitnessUser(@RequestBody KorisnikEntity fitnessUser) {
        KorisnikEntity user = service.loginUser(fitnessUser.getKorisnickoIme(), fitnessUser.getLozinka());
        return new ResponseEntity<KorisnikEntity>(user, HttpStatus.OK);
    }

}
