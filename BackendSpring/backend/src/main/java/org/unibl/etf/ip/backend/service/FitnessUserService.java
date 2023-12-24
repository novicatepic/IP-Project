package org.unibl.etf.ip.backend.service;

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

    public KorisnikEntity createFitnessUser(KorisnikEntity fitnessUser)   {
        KorisnikEntity k = repository.save(fitnessUser);
        codeService.insertCode(k);
        return k;
    }

    public KorisnikEntity updateFitnessUser(KorisnikEntity fitnessUser)   {
        KorisnikEntity k = repository.save(fitnessUser);
        return k;
    }

    public KorisnikEntity loginUser(String username, String password)   {
        return repository.findByKorisnickoImeAndLozinka(username, password);

    }

}
