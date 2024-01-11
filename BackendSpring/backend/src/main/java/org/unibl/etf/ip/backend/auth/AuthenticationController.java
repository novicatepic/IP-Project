package org.unibl.etf.ip.backend.auth;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.exceptions.InvalidUsernameException;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.exceptions.UserTerminatedException;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody AuthRequest request)
            throws InvalidUsernameException, NotFoundException, UserTerminatedException {

        logger.info("User with username " + request.getUsername() + " tried to log in!");

        JwtAuthResponse response = authenticationService.login(request);

        return ResponseEntity.ok(response);

    }

}
