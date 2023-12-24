package org.unibl.etf.ip.backend.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.model.KorisnikEntity;
import org.unibl.etf.ip.backend.repository.FitnessUserRepository;

@Service
public class FitnessUserService {

    @Autowired
    private FitnessUserRepository repository;

    @Autowired
    private CodeService codeService;

    @Autowired
    private MailService mailService;

    public KorisnikEntity createFitnessUser(KorisnikEntity fitnessUser)   {
        KorisnikEntity k = repository.save(fitnessUser);
        String code = codeService.insertCode(k);
        mailService.sendEmail(k.getMail(), "Code for password change", code);
        return k;
    }

    public KorisnikEntity updateFitnessUser(KorisnikEntity fitnessUser)   {
        KorisnikEntity k = repository.save(fitnessUser);
        return k;
    }


    public KorisnikEntity loginUser(String username, String password)   {
        KorisnikEntity k = repository.findByKorisnickoImeAndLozinka(username, password);
        if(k != null && !k.isAktivan()) {
            codeService.deleteCode(k.getId());
            String code = codeService.insertCode(k);
            mailService.sendEmail(k.getMail(), "Code for password change", code);
            return null;
        }
        return k;

    }

}
