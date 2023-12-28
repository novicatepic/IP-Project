package org.unibl.etf.ip.backend.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.exceptions.InvalidUsernameException;
import org.unibl.etf.ip.backend.jtwconfig.JwtService;
import org.unibl.etf.ip.backend.repository.FitnessUserRepository;

@Service
public class AuthenticationService {

    private final FitnessUserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(FitnessUserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public JwtAuthResponse login(AuthRequest request) throws InvalidUsernameException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        System.out.println("IN");
        var user = userRepository.findByKorisnickoIme(request.getUsername())
                .orElseThrow(() -> new InvalidUsernameException());
        System.out.println(user.getKorisnickoIme());
        var jwt = jwtService.generateToken(user);
        //return JwtAuthResponse.builder().token(jwt).build();
        JwtAuthResponse response = new JwtAuthResponse(jwt);
        return response;
    }

}
