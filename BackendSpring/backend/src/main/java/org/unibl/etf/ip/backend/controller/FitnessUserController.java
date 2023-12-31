package org.unibl.etf.ip.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.auth.JwtAuthResponse;
import org.unibl.etf.ip.backend.errorservice.ForbiddenEntity;
import org.unibl.etf.ip.backend.exceptions.ModifiedUserNameException;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.exceptions.UserNotActiveException;
import org.unibl.etf.ip.backend.loginservice.UserLoginHelp;
import org.unibl.etf.ip.backend.model.CodeModel;
import org.unibl.etf.ip.backend.model.KorisnikEntity;
import org.unibl.etf.ip.backend.model.PasswordWrapper;
import org.unibl.etf.ip.backend.service.FitnessUserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/fitness-users")
public class FitnessUserController {

    @Autowired
    private FitnessUserService service;

    @GetMapping("/{id}")
    public ResponseEntity<KorisnikEntity> getFitnessUserById(@PathVariable("id") Integer id) throws NotFoundException {
        if(!UserLoginHelp.checkUserValidity(id)) {
            return ForbiddenEntity.returnForbidden();
        }

        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<KorisnikEntity> getFitnessUserByUsername(@PathVariable("userName") String userName) throws NotFoundException {

        return new ResponseEntity<>(service.getByUsername(userName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<KorisnikEntity> createFitnessUser(@Valid @RequestBody KorisnikEntity fitnessUser) {
        return new ResponseEntity<>(service.createFitnessUser(fitnessUser), HttpStatus.OK);
    }

    @PostMapping("/input-code/{userId}")
    public ResponseEntity<JwtAuthResponse> inputCode(@Valid @PathVariable("userId") Integer userId, @RequestBody CodeModel code) throws NotFoundException {

        return new ResponseEntity<>(service.userInsertCode(userId, code.getCode()), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<KorisnikEntity> updateFitnessUser(@Valid @RequestBody KorisnikEntity fitnessUser) throws UserNotActiveException, NotFoundException, ModifiedUserNameException {
        if(!UserLoginHelp.checkUserValidity(fitnessUser.getId())) {
            return ForbiddenEntity.returnForbidden();
        }

        return new ResponseEntity<>(service.updateFitnessUser(fitnessUser), HttpStatus.OK);
    }

    @PutMapping("/password-update")
    public ResponseEntity<KorisnikEntity> updateFitnessUserPassword(@Valid @RequestBody PasswordWrapper passwordWrapper) throws NotFoundException {
        if(!UserLoginHelp.checkUserValidity(passwordWrapper.getId())) {
            return ForbiddenEntity.returnForbidden();
        }

        return new ResponseEntity<>(service.updateFitnessUserPassword(passwordWrapper), HttpStatus.OK);
    }
}
