package org.unibl.etf.ip.backend.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.ip.backend.exceptions.InvalidUsernameException;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody AuthRequest request) throws InvalidUsernameException, NotFoundException {

        JwtAuthResponse response = authenticationService.login(request);

        System.out.println(response);

        return ResponseEntity.ok(response);

    }

}
