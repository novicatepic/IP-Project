package org.unibl.etf.ip.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.exceptions.ModifiedUserNameException;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.exceptions.UserNotActiveException;
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
        String code = codeService.saveCodeToDB(k);
        mailService.sendEmail(k.getMail(), "Code for password change", code);
        return k;
    }

    public KorisnikEntity updateFitnessUser(KorisnikEntity fitnessUser) throws UserNotActiveException,NotFoundException, ModifiedUserNameException {
        KorisnikEntity currentDBUser = repository.findById(fitnessUser.getId()).orElseThrow(NotFoundException::new);

        if(!currentDBUser.getKorisnickoIme().equals(fitnessUser.getKorisnickoIme())) {
            throw new ModifiedUserNameException();
        }

        if(!currentDBUser.isAktivan()) {
            throw new UserNotActiveException();
        }

        KorisnikEntity k = repository.save(fitnessUser);
        return k;
    }

    public Boolean userInsertCode(Integer userId, String code) throws NotFoundException {
        Boolean result = codeService.insertCode(userId, code);
        if(result) {
            KorisnikEntity k = repository.findById(userId).orElseThrow(NotFoundException::new);
            k.setAktivan(true);
            repository.save(k);
            return true;
        }
        return false;
    }


    public KorisnikEntity loginUser(String username, String password) throws NotFoundException {
        KorisnikEntity k = repository.findByKorisnickoImeAndLozinka(username, password);
        if(k != null) {
            System.out.println("UN " + k.getKorisnickoIme());
        }
        if(k != null && !k.isAktivan()) {
            codeService.deleteCode(k.getId());
            String code = codeService.saveCodeToDB(k);
            mailService.sendEmail(k.getMail(), "Code for password change", code);
            //return null;
        }
        return k;

    }

}
