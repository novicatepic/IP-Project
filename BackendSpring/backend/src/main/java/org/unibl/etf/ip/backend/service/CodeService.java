package org.unibl.etf.ip.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.model.KorisnikEntity;
import org.unibl.etf.ip.backend.model.NalogAktivacijaEntity;
import org.unibl.etf.ip.backend.repository.CodeRepository;

import java.util.Random;

@Service
public class CodeService {

    @Autowired
    private CodeRepository repository;

    public void insertCode(KorisnikEntity fitnessUser) {

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0; i<4; i++) {
            sb.append(random.nextInt(10));
        }

        NalogAktivacijaEntity code = new NalogAktivacijaEntity();
        code.setFitnessUser(fitnessUser);
        code.setKod(sb.toString());

        repository.save(code);
    }

}
