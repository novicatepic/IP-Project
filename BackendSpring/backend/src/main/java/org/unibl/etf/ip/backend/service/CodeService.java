package org.unibl.etf.ip.backend.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.model.KorisnikEntity;
import org.unibl.etf.ip.backend.model.NalogAktivacijaEntity;
import org.unibl.etf.ip.backend.repository.CodeRepository;

import java.util.Random;

@Service
public class CodeService {

    @Autowired
    private CodeRepository repository;

    public String saveCodeToDB(KorisnikEntity fitnessUser) {

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

    public Boolean insertCode(Integer userId, String userCode) throws NotFoundException {
        NalogAktivacijaEntity code  = repository.findByKorisnikId(userId)
                .orElseThrow(() -> new NotFoundException());

        /*System.out.print("Code 1: " + code.getKod());
        System.out.print("Code 2: " +  userCode);*/

        if(code.getKod().equals(userCode)) {
            //System.out.print("True");
            repository.delete(code);
            return true;
        }
        return false;
    }

    @Transactional
    public void deleteCode(Integer fitnessUserId) throws NotFoundException {
        NalogAktivacijaEntity code  = repository.findByKorisnikId(fitnessUserId)
                .orElseThrow(() -> new NotFoundException());

        repository.delete(code);


    }

}
