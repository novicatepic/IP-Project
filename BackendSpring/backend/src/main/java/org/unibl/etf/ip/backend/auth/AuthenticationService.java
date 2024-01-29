package org.unibl.etf.ip.backend.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.exceptions.InvalidUsernameException;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.exceptions.UserTerminatedException;
import org.unibl.etf.ip.backend.jtwconfig.JwtService;
import org.unibl.etf.ip.backend.model.KorisnikEntity;
import org.unibl.etf.ip.backend.model.NalogAktivacijaEntity;
import org.unibl.etf.ip.backend.repository.FitnessUserRepository;
import org.unibl.etf.ip.backend.service.CodeService;
import org.unibl.etf.ip.backend.service.MailService;

@Service
public class AuthenticationService {

    private final FitnessUserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Autowired
    private CodeService codeService;
    @Autowired
    private MailService mailService;

    public AuthenticationService(FitnessUserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager)
     {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public JwtAuthResponse login(AuthRequest request) throws InvalidUsernameException, NotFoundException, UserTerminatedException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        /*var user = userRepository.findByKorisnickoIme(request.getUsername())
                .orElseThrow(() -> new InvalidUsernameException());*/

        var user = loginUser(request);

        if(!user.isAktivan()) {
            return null;
        }

        if(user.getTerminiran()) {
            throw new UserTerminatedException();
        }

        //System.out.println(user.getKorisnickoIme());
        var jwt = jwtService.generateToken(user);

        JwtAuthResponse response = new JwtAuthResponse(jwt);
        return response;
    }

    public KorisnikEntity loginUser(AuthRequest request) throws InvalidUsernameException, NotFoundException {
        KorisnikEntity k = userRepository.findByKorisnickoIme(request.getUsername())
                .orElseThrow(InvalidUsernameException::new);
        if(k != null && !k.isAktivan()) {
            NalogAktivacijaEntity codeFromDatabase = codeService.getById(k.getId());
            if(codeFromDatabase != null) {
                codeService.deleteCode(k.getId());
            }
            String code = codeService.saveCodeToDB(k);
            mailService.sendEmail(k.getMail(), "Code for activating account", code);
        }
        return k;

    }

}
