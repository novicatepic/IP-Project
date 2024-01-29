package org.unibl.etf.ip.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.auth.JwtAuthResponse;
import org.unibl.etf.ip.backend.controller.FitnessProgramController;
import org.unibl.etf.ip.backend.controller.FitnessUserController;
import org.unibl.etf.ip.backend.exceptions.ModifiedUserNameException;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.exceptions.UserNotActiveException;
import org.unibl.etf.ip.backend.jtwconfig.JwtService;
import org.unibl.etf.ip.backend.model.KorisnikEntity;
import org.unibl.etf.ip.backend.model.PasswordHelper;
import org.unibl.etf.ip.backend.model.PasswordWrapper;
import org.unibl.etf.ip.backend.repository.FitnessUserRepository;

import java.util.List;

@Service
public class FitnessUserService {

    private Logger logger = LoggerFactory.getLogger(FitnessUserService.class);

    @Autowired
    private FitnessUserRepository repository;

    @Autowired
    private CodeService codeService;

    @Autowired
    private MailService mailService;

    @Autowired
    private JwtService jwtService;


    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public PasswordHelper encodePassword(PasswordHelper ph) {
        PasswordHelper result = new PasswordHelper(passwordEncoder().encode(ph.getPassword()));
        return result;
    }

    public KorisnikEntity createFitnessUser(KorisnikEntity fitnessUser)   {
        fitnessUser.setLozinka(passwordEncoder().encode(fitnessUser.getLozinka()) );
        KorisnikEntity k = repository.save(fitnessUser);
        String code = codeService.saveCodeToDB(k);
        mailService.sendEmail(k.getMail(), "Code for profile activation", code);
        logger.info("New attempt to create account for user with username " + fitnessUser.getUsername() + " and email " + fitnessUser.getMail());
        return k;
    }

    public KorisnikEntity getById(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<KorisnikEntity> getAllButMe(Integer id) throws NotFoundException {
        List<KorisnikEntity> all = repository.findAll();
        return all.stream().filter((x) -> x.getId() != id).toList();
    }

    public KorisnikEntity getByUsername(String userName) throws NotFoundException {
        return repository.findByKorisnickoIme(userName).orElseThrow(NotFoundException::new);
    }

    public KorisnikEntity updateFitnessUser(KorisnikEntity fitnessUser) throws UserNotActiveException,NotFoundException, ModifiedUserNameException {
        KorisnikEntity currentDBUser = repository.findById(fitnessUser.getId()).orElseThrow(NotFoundException::new);

        if(!currentDBUser.getKorisnickoIme().equals(fitnessUser.getKorisnickoIme())) {
            throw new ModifiedUserNameException();
        }

        if(!currentDBUser.isAktivan()) {
            throw new UserNotActiveException();
        }

        fitnessUser.setLozinka(currentDBUser.getLozinka());
        KorisnikEntity k = repository.save(fitnessUser);
        return k;
    }

    public JwtAuthResponse userInsertCode(Integer userId, String code) throws NotFoundException {
        Boolean result = codeService.insertCode(userId, code);
        if(result) {
            KorisnikEntity k = repository.findById(userId).orElseThrow(NotFoundException::new);
            k.setAktivan(true);
            repository.save(k);
            String token = jwtService.generateToken((k));
            return new JwtAuthResponse(token);
        }
        return null;
    }

    public KorisnikEntity updateFitnessUserPassword(PasswordWrapper passwordWrapper) throws NotFoundException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        KorisnikEntity user = repository.findById(passwordWrapper.getId()).orElseThrow(NotFoundException::new);

        boolean matches = passwordEncoder.matches(passwordWrapper.getOldPassword(), user.getLozinka());


        if(!matches) {
            throw new NotFoundException();
        }

        user.setLozinka(passwordEncoder().encode(passwordWrapper.getNewPassword()));

        return repository.save(user);

    }
}
