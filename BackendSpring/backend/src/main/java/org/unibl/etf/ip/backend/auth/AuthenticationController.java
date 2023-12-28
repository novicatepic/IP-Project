package org.unibl.etf.ip.backend.auth;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.ip.backend.exceptions.InvalidUsernameException;
import org.unibl.etf.ip.backend.jtwconfig.JwtService;
import org.unibl.etf.ip.backend.repository.FitnessUserRepository;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody AuthRequest request) throws InvalidUsernameException {
        logger.info("Received login request for user: {}", request.getUsername());

        try {
            JwtAuthResponse response = authenticationService.login(request);
            return ResponseEntity.ok(response);
        } catch (InvalidUsernameException e) {
            logger.error("Error during login: {}", e.getMessage());
            throw e;
        }
    }

}
