package org.unibl.etf.ip.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.model.LokacijaEntity;
import org.unibl.etf.ip.backend.repository.LokacijaRepository;

@Service
public class LokacijaService {

    @Autowired
    private LokacijaRepository repository;

    public LokacijaEntity insertLocation(LokacijaEntity entity) {
        return this.repository.save(entity);
    }

}
