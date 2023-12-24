package org.unibl.etf.ip.backend.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.model.KorisnikEntity;
import org.unibl.etf.ip.backend.model.NalogAktivacijaEntity;
import org.unibl.etf.ip.backend.repository.CodeRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class CodeService {

    @Autowired
    private CodeRepository repository;

    public String insertCode(KorisnikEntity fitnessUser) {

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0; i<4; i++) {
            sb.append(random.nextInt(10));
        }

        NalogAktivacijaEntity code = new NalogAktivacijaEntity();
        code.setFitnessUser(fitnessUser);
        code.setKod(sb.toString());
        code.setKorisnikId(fitnessUser.getId());

        repository.save(code);

        return sb.toString();
    }

    @Transactional
    public void deleteCode(Integer fitnessUserId) throws NotFoundException {
        NalogAktivacijaEntity code  = repository.findByKorisnikId(fitnessUserId)
                .orElseThrow(() -> new NotFoundException());

        repository.delete(code);


    }

}
